package com.alireza.service;


import com.alireza.model.Patient;
import com.alireza.repository.PatientRepository;

public class PatientService {

    public void createPatient(Patient patient) {
        PatientRepository.createPatient(patient);
    }

    public Patient findPatient(String username, String nationalCode) {
        return PatientRepository.findPatient(username, nationalCode);
    }
}
