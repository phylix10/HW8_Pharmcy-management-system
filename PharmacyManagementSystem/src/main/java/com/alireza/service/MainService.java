package com.alireza.service;

import com.alireza.model.*;

import java.util.List;

public class MainService {
    private Admin loggedInAdmin = null;
    private Admin loggedInSuperAdmin = null;
    private Patient loggedInPatient = null;
    AdminService adminService = new AdminService();
    PatientService patientService = new PatientService();
    PrescriptionService prescriptionService = new PrescriptionService();
    PrescriptionItemService prescriptionItemService = new PrescriptionItemService();
    ItemService itemService = new ItemService();

    public void register(Patient patient) {
        if (patientService.findPatient(patient.getUsername(), patient.getNationalCode()) == null) {
            patientService.createPatient(patient);
            System.out.println("Registration was successful :)");
        } else {
            System.out.println("An Patient with this username or national code has already been registered!, login please");
        }
    }

    public boolean loginPatient(String username, String password, String nationalCode) {
        Patient patient = patientService.findPatient(username, nationalCode);
        if (patient != null) {
            if (patient.getPassword().equals(password)) {
                loggedInPatient = patient;
                return true;
            } else {
                System.out.println("The password is not correct!");
                return false;
            }
        } else {
            return false;
        }
    }

    public Integer createPrescription() {
        return prescriptionService.addPrescription(loggedInPatient);
    }

    public void showAllItem() {
        List<Item> itemList = itemService.showAllItem();
        System.out.println("_______________________________");
        System.out.println("| Id  |     Name    | IsExist |");
        System.out.println("_______________________________");
        for (Item item : itemList) {
            System.out.printf("|  %-3s|%-13s|   %-6s|%n",
                    item.getId(),
                    item.getName(),
                    item.isExist());
            System.out.println("_______________________________");
        }
    }

    public void addItemToPrescription(int prescriptionId, int itemId, int countItem) {
        prescriptionItemService.addItemToPrescription(prescriptionId, itemId, countItem);
        System.out.println("The desired item has been successfully added");
    }

    public boolean showConfirmedPrescription() {
        List<Prescription> prescriptionList = prescriptionService.showConfirmedPrescription(loggedInPatient);
        if (prescriptionList.size() != 0) {
            System.out.println("_____________________________________________________");
            System.out.println("| Id  |  Patient Name  | IsConfirmed |  Total Price |");
            System.out.println("_____________________________________________________");
            for (Prescription prescription : prescriptionList) {
                System.out.printf("|  %-3s|  %-14s|    %-9s|     %-9s|%n",
                        prescription.getId(),
                        prescription.getPatientId().getName(),
                        prescription.isConfirmed(),
                        prescription.getTotalPrice());
                System.out.println("_____________________________________________________");
            }
            return true;
        }
        else {
            return false;
        }
    }

        public void showPrescriptionDetail ( int prescriptionId){
            List<PrescriptionItem> prescriptionItemList = prescriptionItemService.showDetailPrescription(prescriptionId);
            System.out.println("________________________________________________________________________________________");
            System.out.println("| Id  | Item Id  |  Item Name  | Item Exist | Item Count | Item Price | PrescriptionId |");
            System.out.println("________________________________________________________________________________________");
            for (PrescriptionItem prescriptionItem : prescriptionItemList) {
                System.out.printf("|  %-3s|    %-6s|%-13s|    %-8s|     %-7s|    %-8s|        %-8s|%n",
                        prescriptionItem.getId(),
                        prescriptionItem.getItemId().getId(),
                        prescriptionItem.getItemId().getName(),
                        prescriptionItem.getItemId().isExist(),
                        prescriptionItem.getCountItem(),
                        prescriptionItem.getItemId().getPrice(),
                        prescriptionItem.getPrescriptionId().getId());
                System.out.println("________________________________________________________________________________________");
            }
        }

        public boolean showAllPrescription () {
            List<Prescription> prescriptionList = prescriptionService.showAllPrescription(loggedInPatient);
            if (prescriptionList.size() != 0) {
                System.out.println("_____________________________________________________");
                System.out.println("| Id  |  Patient Name  | IsConfirmed |  Total Price |");
                System.out.println("_____________________________________________________");
                for (Prescription prescription : prescriptionList) {
                    System.out.printf("|  %-3s|  %-14s|    %-9s|     %-9s|%n",
                            prescription.getId(),
                            prescription.getPatientId().getName(),
                            prescription.isConfirmed(),
                            prescription.getTotalPrice());
                    System.out.println("_____________________________________________________");
                }
                return true;
            }
            else {
                return false;
            }
        }
        public void updatePrescriptionConfirmed ( int prescriptionId){
            prescriptionService.updateIsConfirmedPrescription(prescriptionId, loggedInPatient);
        }

        public void changeCountItem ( int countItem, int prescriptionId, int itemId){
            prescriptionItemService.updateCountItem(countItem, prescriptionId, itemId);
            System.out.println("The prescription has been successfully updated");
        }

        public void deleteAnItemFromPrescription ( int prescriptionId, int itemId){
            prescriptionItemService.deleteItemFromPrescription(prescriptionId, itemId);
            System.out.println("The desired item was successfully deleted");
        }

        public void deletePrescription (int prescriptionId){
            prescriptionService.deletePrescription(prescriptionId, loggedInPatient);
            System.out.println("Prescription successfully deleted");
        }

        public void deleteAllItem (int prescriptionId){
            prescriptionItemService.deleteAllItemFromPrescription(prescriptionId);
        }

        public boolean loginAdmin (String username, String password, String nationalCode){
            Admin admin = adminService.findAdmin(username, nationalCode);
            if (admin != null) {
                if (admin.getPassword().equals(password)) {
                    loggedInAdmin = admin;
                    return true;
                } else {
                    System.out.println("The password is not correct!");
                    return false;
                }
            } else {
                return false;
            }
        }

        public boolean showAllNotConfirmedPrescription () {
            List<Prescription> prescriptionList = prescriptionService.showAllInConfirmedPrescription();
            if (prescriptionList.size() != 0) {
                System.out.println("___________________________________________________");
                System.out.println("| Id  |  Patient Id  | IsConfirmed |  Total Price |");
                System.out.println("___________________________________________________");
                for (Prescription prescription : prescriptionList) {
                    System.out.printf("|  %-3s|      %-8s|    %-9s|     %-9s|%n",
                            prescription.getId(),
                            prescription.getPatientId().getId(),
                            prescription.isConfirmed(),
                            prescription.getTotalPrice());
                    System.out.println("___________________________________________________");
                }
                return true;
            }
            else {
                return false;
            }
        }

        public void setPrescriptionTotalPrice ( int totalPrice, int prescriptionId){
            prescriptionService.updatePrescriptionByAdmin(totalPrice, prescriptionId);
            System.out.println("Prescription successfully verified");
        }

        public void addItem (Item item){
            if (itemService.createItem(item)) {
                System.out.println("Item added successfully");
            } else {
                System.out.println("An item has already been created with this name!");
            }
        }

        public void showAllItemForAdmin () {
            List<Item> itemList = itemService.showAllItem();
            System.out.println("_________________________________________");
            System.out.println("| Id  |     Name    | IsExist | Price   |");
            System.out.println("_________________________________________");
            for (Item item : itemList) {
                System.out.printf("|  %-3s|%-13s|   %-6s|  %-7s|%n",
                        item.getId(),
                        item.getName(),
                        item.isExist(),
                        item.getPrice());
                System.out.println("_________________________________________");
            }
        }

        public void editItem (Item item){
            itemService.updateItem(item);
            System.out.println("The desired item has been successfully updated");
        }

        public void deleteItem (Item item){
            itemService.deleteItem(item);
            System.out.println("The desired item has been successfully deleted");
        }

        public boolean loginSuperAdmin () {
            Admin superAdmin = adminService.findSuperAdmin(loggedInAdmin.getUsername());
            if (superAdmin != null) {
                loggedInSuperAdmin = superAdmin;
                return true;
            } else {
                System.out.println("You are not super admin");
                return false;
            }
        }

        public void addAdmin (Admin admin){
            if (adminService.createAdmin(admin)) {
                System.out.println("Admin creation was done successfully");
            } else {
                System.out.println("An admin with this username or national code has already been created!");
            }
        }

        public void showAllAdmin () {
            List<Admin> adminList = adminService.showAllAdmin();
            System.out.println("____________________________________________________________________________________________");
            System.out.println("| Id  |      Name     |  Username     |   Password     | Age  |   National Code  |  Role   |");
            System.out.println("____________________________________________________________________________________________");
            for (Admin admin : adminList) {
                System.out.printf("|  %-3s| %-14s| %-14s|  %-14s|  %-4s|   %-15s|  %-7s|%n",
                        admin.getId(),
                        admin.getName(),
                        admin.getUsername(),
                        admin.getPassword(),
                        admin.getAge(),
                        admin.getNationalCode(),
                        admin.getRole());
                System.out.println("____________________________________________________________________________________________");
            }
        }

        public void editAdmin (Admin admin){
            adminService.updateAdmin(admin);
            System.out.println("An admin was edited successfully");
        }

        public void deleteAdmin (Admin admin){
            adminService.deleteAdmin(admin);
            System.out.println("An admin was deleted successfully");
        }
    }
