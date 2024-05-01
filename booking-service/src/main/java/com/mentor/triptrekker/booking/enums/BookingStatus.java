package com.mentor.triptrekker.booking.enums;

import jakarta.annotation.Resource;

public enum BookingStatus {
    UNCONFIRMED,
    CONFIRMED,
    PAYMENT_FAILURE,
    PAYMENT_CANCELLED;
}
