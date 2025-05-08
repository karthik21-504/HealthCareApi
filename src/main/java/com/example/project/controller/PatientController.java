package com.example.project.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.Patient;
import com.example.project.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
  @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public Object getAllPatients() {
        return patientService.getAllPatients().toString();
    }

    @PostMapping("/register")
    public JSONObject addPatient(@RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }

}
