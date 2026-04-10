package com.siddhesh.aadhaar.repository;

import com.siddhesh.aadhaar.entity.Aadhaar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AadhaarRepository extends JpaRepository<Aadhaar, Long> {
    boolean existsByAadhaarNumber(String aadhaarNumber);
    void deleteByPersonId(Long personId);
}