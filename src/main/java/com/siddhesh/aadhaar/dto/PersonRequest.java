package com.siddhesh.aadhaar.dto;

import lombok.Data;

@Data
public class PersonRequest {
    private String name;
    private Integer age;
    private String email;
    private String city;

    // Aadhaar fields — nested in the same request
    private String aadhaarNumber;
    private String address;
    private String dateOfBirth;
}