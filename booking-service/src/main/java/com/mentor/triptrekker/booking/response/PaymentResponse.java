package com.mentor.triptrekker.booking.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PaymentResponse {
    private Integer paymentId;
    private Integer bookingId;
}
