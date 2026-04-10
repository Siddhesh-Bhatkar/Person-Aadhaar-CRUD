package com.siddhesh.aadhaar.service;

import com.siddhesh.aadhaar.dto.PersonRequest;
import com.siddhesh.aadhaar.dto.PersonResponse;
import com.siddhesh.aadhaar.entity.Aadhaar;
import com.siddhesh.aadhaar.entity.Person;
import com.siddhesh.aadhaar.repository.AadhaarRepository;
import com.siddhesh.aadhaar.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepo;
    private final AadhaarRepository aadhaarRepo;

    @Override
    public PersonResponse createPerson(PersonRequest req) {
        if (personRepo.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email already exists: " + req.getEmail());
        if (aadhaarRepo.existsByAadhaarNumber(req.getAadhaarNumber()))
            throw new RuntimeException("Aadhaar number already linked.");

        Person person = Person.builder()
                .name(req.getName())
                .age(req.getAge())
                .email(req.getEmail())
                .city(req.getCity())
                .build();
        Person saved = personRepo.save(person);

        Aadhaar aadhaar = Aadhaar.builder()
                .aadhaarNumber(req.getAadhaarNumber())
                .address(req.getAddress())
                .dateOfBirth(req.getDateOfBirth())
                .person(saved)
                .build();
        aadhaarRepo.save(aadhaar);
        saved.setAadhaar(aadhaar);

        return toResponse(saved, aadhaar);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse getPersonById(Long id) {
        Person p = personRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found: " + id));
        return toResponse(p, p.getAadhaar());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> getAllPersons() {
        return personRepo.findAll().stream()
                .map(p -> toResponse(p, p.getAadhaar()))
                .collect(Collectors.toList());
    }

    @Override
    public PersonResponse updatePerson(Long id, PersonRequest req) {
        Person p = personRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Person not found: " + id));
        p.setName(req.getName());
        p.setAge(req.getAge());
        p.setEmail(req.getEmail());
        p.setCity(req.getCity());

        Aadhaar a = p.getAadhaar();
        if (a != null) {
            a.setAddress(req.getAddress());
            a.setDateOfBirth(req.getDateOfBirth());
            aadhaarRepo.save(a);
        }
        personRepo.save(p);
        return toResponse(p, a);
    }

    @Override
    public void deletePerson(Long id) {
        if (!personRepo.existsById(id))
            throw new RuntimeException("Person not found: " + id);
        aadhaarRepo.deleteByPersonId(id);
        personRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse getPersonByEmail(String email) {
        Person p = personRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No person with email: " + email));
        return toResponse(p, p.getAadhaar());
    }

    private PersonResponse toResponse(Person p, Aadhaar a) {
        return PersonResponse.builder()
                .personId(p.getId())
                .name(p.getName())
                .age(p.getAge())
                .email(p.getEmail())
                .city(p.getCity())
                .aadhaarNumber(a != null ? a.getAadhaarNumber() : null)
                .address(a != null ? a.getAddress() : null)
                .dateOfBirth(a != null ? a.getDateOfBirth() : null)
                .build();
    }
}