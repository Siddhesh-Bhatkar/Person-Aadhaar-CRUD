package com.siddhesh.aadhaar.service;

import com.siddhesh.aadhaar.dto.PersonRequest;
import com.siddhesh.aadhaar.dto.PersonResponse;
import java.util.List;


public interface PersonService {
	
    PersonResponse createPerson(PersonRequest request);
    PersonResponse getPersonById(Long id);
    List<PersonResponse> getAllPersons();
    PersonResponse updatePerson(Long id, PersonRequest request);
    void deletePerson(Long id);
    PersonResponse getPersonByEmail(String email);
}