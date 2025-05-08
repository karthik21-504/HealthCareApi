package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.Model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String> {

}
