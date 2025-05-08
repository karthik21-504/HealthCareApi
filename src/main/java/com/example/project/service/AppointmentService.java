package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Model.Appointment;
import com.example.project.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public JSONObject deleteAppointment(String appointmentId) {
        JSONObject response = new JSONObject();

        Optional<Appointment> appointmentOpt = appointmentRepository.findById(appointmentId);
        if (appointmentOpt.isPresent()) {
            appointmentRepository.deleteById(appointmentId);
            response.put("status", "success");
            response.put("message", "Appointment deleted successfully.");
        } else {
            response.put("status", "error");
            response.put("message", "Appointment not found.");
        }

        return response;
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public JSONObject getAllAppointmentsJson() {
        List<Appointment> appointments = appointmentRepository.findAll();
        JSONArray appointmentArray = new JSONArray();
        for (Appointment appointment : appointments) {
            JSONObject appointmentJson = new JSONObject();
            appointmentJson.put("booking_id", appointment.getBooking_id());
            appointmentJson.put("disease", appointment.getDisease());
            appointmentJson.put("patientId", appointment.getPatient_id());
            appointmentJson.put("priority", appointment.getPriority());

            appointmentArray.put(appointmentJson);
        }

        JSONObject response = new JSONObject();
        response.put("appointments", appointmentArray);

        return response;
    }

    public JSONObject addAppointment(Appointment appointment) {
        JSONObject response = new JSONObject();
        try {
            Appointment saved = appointmentRepository.save(appointment);
            response.put("status", "success");
            response.put("appointmentId", saved.getBooking_id());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to save appointment.");
        }
        return response;
    }

    public JSONObject getAppointmentsByPatient(String patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        JSONArray appointmentArray = new JSONArray();
       for (Appointment appointment : appointments) {
            JSONObject appointmentJson = new JSONObject();
            appointmentJson.put("booking_id", appointment.getBooking_id());
            appointmentJson.put("disease", appointment.getDisease());
            appointmentJson.put("patientId", appointment.getPatient_id());
            appointmentJson.put("priority", appointment.getPriority());

            appointmentArray.put(appointmentJson);
        }
        JSONObject response = new JSONObject();
        response.put("appointments", appointmentArray);

        return response;
    }
}
