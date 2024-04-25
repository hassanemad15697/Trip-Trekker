package com.mentor.triptrekker.booking.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FareDetailBySegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String segmentId;

    private String cabin;

    private String fareBasis;

    private String clazz;

    @Embedded
    private IncludedCheckedBags includedCheckedBags;
}
