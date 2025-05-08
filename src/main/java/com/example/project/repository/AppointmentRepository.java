package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.project.Model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,String>{
	@Query("SELECT a from Appointment a WHERE a.patient_id = :patient_id")
  List<Appointment> findByPatientId(@Param("patient_id") String patient_id);
}
