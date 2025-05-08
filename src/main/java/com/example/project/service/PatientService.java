package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Model.Patient;
import com.example.project.repository.PatientRepository;

@Service
public class PatientService {
  @Autowired
    private PatientRepository patientRepository;

    public JSONObject getPatientById(String patientId) {
        JSONObject response = new JSONObject();
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (patientOpt.isPresent()) {
            Patient patient = patientOpt.get();
            response.put("status", "success");
            response.put("patientId", patient.getPatient_Id());
            response.put("name", patient.getPatient_name());
            response.put("email", patient.getEmail());
        } else {
            response.put("status", "error");
            response.put("message", "Patient not found");
        }

        return response;
    }

    public JSONObject getAllPatients() {
        JSONObject response = new JSONObject();
        List<Patient> patients = patientRepository.findAll();
        JSONArray patientArray = new JSONArray();
        
        if (!patients.isEmpty()) {
            response.put("status", "success");
            for(Patient p: patients) {
            	JSONObject pt = new JSONObject();
            	pt.put("patientId", p.getPatient_Id());
            	pt.put("patient_name", p.getPatient_name());
            	pt.put("email", p.getEmail());
            	pt.put("mobile_number", p.getMobile_number());
            	pt.put("date_of_birth", p.getDate_of_birth());
            	
            	patientArray.put(pt);
            }
            response.put("patients", patientArray);
        } else {
            response.put("status", "error");
            response.put("message", "No patients found");
        }
        return response;
    }

    public JSONObject addPatient(Patient patient) {
        JSONObject response = new JSONObject();

        try {
            Patient savedPatient = patientRepository.save(patient);
            response.put("status", "success");
            response.put("patientId", savedPatient.getPatient_Id());
            response.put("message", "Patient added successfully");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Error adding patient: " + e.getMessage());
        }

        return response;
    }

    public JSONObject updatePatient(String patientId, Patient updatedPatient) {
        JSONObject response = new JSONObject();

        Optional<Patient> existingPatientOpt = patientRepository.findById(patientId);
        
        if (existingPatientOpt.isPresent()) {
            Patient existingPatient = existingPatientOpt.get();
            existingPatient.setPatient_name(updatedPatient.getPatient_name());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setDate_of_birth(updatedPatient.getDate_of_birth());

            Patient savedPatient = patientRepository.save(existingPatient);
            
            response.put("status", "success");
            response.put("patientId", savedPatient.getPatient_Id());
            response.put("message", "Patient updated successfully");
        } else {
            response.put("status", "error");
            response.put("message", "Patient not found");
        }

        return response;
    }

    public JSONObject deletePatient(String patientId) {
        JSONObject response = new JSONObject();
        Optional<Patient> patientOpt = patientRepository.findById(patientId);

        if (patientOpt.isPresent()) {
            patientRepository.deleteById(patientId);
            response.put("status", "success");
            response.put("message", "Patient deleted successfully");
        } else {
            response.put("status", "error");
            response.put("message", "Patient not found");
        }

        return response;
    }

}
