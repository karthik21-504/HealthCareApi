package com.example.project.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
    private String patient_Id;
    private String patient_name;
    private String email;
    private String mobile_number;
    private Date date_of_birth;

    public Patient() {
    }

    public Patient(String patient_name, String email, String mobile_number, Date date_of_birth) {
        this.patient_name = patient_name;
        this.email = email;
        this.mobile_number = mobile_number;
        this.date_of_birth = date_of_birth;
    }
    
    public String getPatient_Id() {
        return patient_Id;
    }

    public void setPatient_Id(String patient_Id) {
        this.patient_Id = patient_Id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

}
