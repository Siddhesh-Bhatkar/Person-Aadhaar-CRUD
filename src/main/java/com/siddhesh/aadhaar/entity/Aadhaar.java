package com.siddhesh.aadhaar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aadhaar_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Aadhaar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 12)
    private String aadhaarNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String dateOfBirth;   // format: DD-MM-YYYY

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    @ToString.Exclude
    private Person person;
}