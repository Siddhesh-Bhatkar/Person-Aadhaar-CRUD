package com.siddhesh.aadhaar.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {
    private Long personId;
    private String name;
    private Integer age;
    private String email;
    private String city;
    private String aadhaarNumber;
    private String address;
    private String dateOfBirth;
}