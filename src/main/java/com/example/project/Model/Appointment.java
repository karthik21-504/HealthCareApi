package com.example.project.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Appointment {
    @Id
    private String booking_id;
    private String disease;
    private Date appointment_date;
    private String priority;
    private String patient_id;

    public Appointment() {
    }

    public Appointment(String disease, Date appointment_date, String priority, String patient_id) {
        this.disease = disease;
        this.appointment_date = appointment_date;
        this.priority = priority;
        this.patient_id = patient_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
}