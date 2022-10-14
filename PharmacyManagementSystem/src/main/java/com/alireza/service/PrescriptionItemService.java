package com.alireza.service;

import com.alireza.model.PrescriptionItem;
import com.alireza.repository.PrescriptionItemRepository;

import java.util.List;

public class PrescriptionItemService {
    public void addItemToPrescription(int prescriptionId, int itemId, int countItem){
        PrescriptionItemRepository.addItemToPrescription(prescriptionId, itemId, countItem);
        System.out.println("Item added successfully");
    }
    public List<PrescriptionItem> showDetailPrescription(int prescriptionId){
        return PrescriptionItemRepository.showDetailPrescription(prescriptionId);
    }

    public void deleteItemFromPrescription(int prescriptionId, int itemId){
        PrescriptionItemRepository.deleteItemFromPrescription(prescriptionId, itemId);
    }

    public void updateCountItem(int countItem, int prescriptionId, int itemId){
        PrescriptionItemRepository.updateCountItem(countItem, prescriptionId, itemId);
    }

    public void deleteAllItemFromPrescription(int prescriptionId){
        PrescriptionItemRepository.deleteAllItemFromPrescription(prescriptionId);
    }
}
