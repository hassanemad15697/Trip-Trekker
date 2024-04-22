package com.mentor.triptrekker.auth.model;

import lombok.Getter;

@Getter
public enum ValidationType {

    BOOKING_FLIGHT("validation/booking/flight");


    private String value;

    ValidationType(String value) {
        this.value = value;
    }
}
