package com.mentor.triptrekker.payment.controller;


import com.mentor.triptrekker.payment.request.PaymentRequest;
import com.mentor.triptrekker.payment.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
@Controller
@RequiredArgsConstructor
@Slf4j
public class PaypalController {

    private final PaypalService paypalService;


    @PostMapping("/payment/create")
    public RedirectView createPayment(@RequestBody PaymentRequest request, HttpServletResponse httpServletResponse) {
        try {
            Payment payment = paypalService.createPayment(request);

            for (Links links: payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    System.out.println("approval_url: "+links.getHref());
//                   httpServletResponse.setHeader("Location", links.getHref());
//                    httpServletResponse.setStatus(302);
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/payment/success")
    public void paymentSuccess(@RequestParam("bookingId") Long bookingId,@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            paypalService.executePayment(bookingId,paymentId, payerId);

        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
        }
    }

    @GetMapping("/payment/cancel")
    public void paymentCancel(@RequestParam("bookingId") Long bookingId) {
        paypalService.callBookingServiceToCancel(bookingId);
    }

    @GetMapping("/payment/error")
    public void paymentError(@RequestParam("bookingId") Long bookingId) {
        paypalService.callBookingServiceToError(bookingId);
    }
}