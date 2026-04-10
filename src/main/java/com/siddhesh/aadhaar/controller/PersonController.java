package com.siddhesh.aadhaar.controller;

import com.siddhesh.aadhaar.dto.PersonRequest;
import com.siddhesh.aadhaar.dto.PersonResponse;
import com.siddhesh.aadhaar.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    // POST /api/persons
    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestBody PersonRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(personService.createPerson(req));
    }

    // GET /api/persons
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAll() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    // GET /api/persons/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    // GET /api/persons/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<PersonResponse> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(personService.getPersonByEmail(email));
    }

    // PUT /api/persons/{id}
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id,
                                                  @RequestBody PersonRequest req) {
        return ResponseEntity.ok(personService.updatePerson(id, req));
    }

    // DELETE /api/persons/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.ok("Person with ID " + id + " deleted successfully.");
    }
}