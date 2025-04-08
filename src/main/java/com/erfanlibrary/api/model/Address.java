package com.erfanlibrary.api.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity @Setter @Getter
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "address_line_1", nullable = false)
    String addressLine1;

    @Column(name = "address_line_2", nullable = true)
    String addressLine2;

    @Column(name = "city", nullable = false)
    String city;

    @Column(name = "state", nullable = false)
    String state;

    @Column(name = "country", nullable = false)
    String country;

    @Column(name = "postal_code", nullable = false)
    String postalCode;
}
