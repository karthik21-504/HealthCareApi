package com.example.project.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.service.AppointmentService;


@RestController
@RequestMapping("/appointment")
public class AppointmentController {
  @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/list")
    public JSONObject getAllAppointments() {
        return appointmentService.getAllAppointmentsJson();
    }

    @GetMapping("/view/{appointmentId}")
    public JSONObject viewAppointment(@PathVariable String appointmentId) {
        return appointmentService.getAppointmentsByPatient(appointmentId);
    }
}



