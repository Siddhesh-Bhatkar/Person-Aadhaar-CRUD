package com.siddhesh.aadhaar.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String city;

    @OneToOne(mappedBy = "person",
              cascade = CascadeType.ALL,
              fetch = FetchType.LAZY,
              orphanRemoval = true)
    private Aadhaar aadhaar;
}