package com.mentor.triptrekker.booking.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class IncludedCheckedBags {

    private int weight;

    private String weightUnit;
}