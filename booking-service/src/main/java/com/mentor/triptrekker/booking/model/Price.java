package com.mentor.triptrekker.booking.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;


    private String total;

    private String base;

    @OneToMany(mappedBy = "price", cascade = CascadeType.ALL)
    private List<Fee> fees;

    private String grandTotal;
}