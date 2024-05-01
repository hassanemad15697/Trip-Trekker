package com.mentor.triptrekker.payment.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {
    Long bookingId;
    Double total;
    String currency;
    String method;
    String description;
}
