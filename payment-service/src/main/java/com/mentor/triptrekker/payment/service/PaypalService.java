package com.mentor.triptrekker.payment.service;

import com.mentor.triptrekker.payment.request.PaymentRequest;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale ;

@Service
@RequiredArgsConstructor
public class PaypalService {

    private final APIContext apiContext;
    private final RestTemplate restTemplate;

    String cancelUrl = "http://localhost:8087/payment/cancel?bookingId=";
    String successUrl = "http://localhost:8087/payment/success?bookingId=";

    public Payment createPayment(PaymentRequest request) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(request.getCurrency());
        amount.setTotal(String.format(Locale.forLanguageTag(request.getCurrency()), "%.2f", request.getTotal())); // 9.99$ - 9,99€

        Transaction transaction = new Transaction();
        transaction.setDescription(request.getDescription());
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(request.getMethod());

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl+request.getBookingId());
        redirectUrls.setReturnUrl(successUrl+request.getBookingId());

        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    public void executePayment(Long bookingId,String paymentId,String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        payment = payment.execute(apiContext, paymentExecution);

        if (payment.getState().equals("approved")) {
            System.out.println( "paymentSuccess");
            //save in the db
            callBookingServiceToConfirm(bookingId);
        }
    }

    private HttpStatusCode callBookingServiceToConfirm(Long bookingId) {
        return restTemplate.getForObject(
                "http://localhost:8082/booking/confirm?bookingId="+bookingId,
                HttpStatusCode.class
        );
    }
    public HttpStatusCode callBookingServiceToCancel(Long bookingId) {
        return restTemplate.getForObject(
                "http://localhost:8082/booking/cancel?bookingId="+bookingId,
                HttpStatusCode.class
        );
    }
    public HttpStatusCode callBookingServiceToError(Long bookingId) {
        return restTemplate.getForObject(
                "http://localhost:8082/booking/error?bookingId="+bookingId,
                HttpStatusCode.class
        );
    }
}