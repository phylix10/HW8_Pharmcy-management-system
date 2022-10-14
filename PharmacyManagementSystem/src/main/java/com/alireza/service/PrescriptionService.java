package com.alireza.service;

import com.alireza.model.Patient;
import com.alireza.model.Prescription;
import com.alireza.repository.PrescriptionRepository;

import java.util.List;

public class PrescriptionService {
    public Integer addPrescription(Patient patient){
        return PrescriptionRepository.createPrescription(patient);
    }

    public List<Prescription> showConfirmedPrescription(Patient patient){
        return PrescriptionRepository.showConfirmedPrescription(patient);
    }

    public List<Prescription> showAllPrescription(Patient patient){
        return PrescriptionRepository.showAllPrescription(patient);
    }

    public void updateIsConfirmedPrescription(int prescriptionId, Patient patient){
        PrescriptionRepository.updateIsConfirmedPrescription(prescriptionId, patient);
    }

    public void deletePrescription(int id, Patient patient){
        PrescriptionRepository.deletePrescription(id, patient);
    }

    public List<Prescription> showAllInConfirmedPrescription(){
        return PrescriptionRepository.showAllInConfirmedPrescription();
    }

    public void updatePrescriptionByAdmin(int totalPrice, int prescriptionId){
        PrescriptionRepository.updatePrescriptionByAdmin(totalPrice, prescriptionId);
    }
}
