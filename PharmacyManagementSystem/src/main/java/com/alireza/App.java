package com.alireza;

import com.alireza.model.Admin;
import com.alireza.model.Item;
import com.alireza.model.Patient;
import com.alireza.service.MainService;

import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MainService mainService = new MainService();

        boolean flag = true;

        while (flag){
            printMainMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input){
                case 1:
                    register(mainService);
                    break;
                case 2:
                    login(mainService);
                    break;
                case 3:
                    admin(mainService);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void printMainMenu() {
        System.out.println(
                "――――― Welcome To The Pharmacy Management System ――――――\n " +
                        "✎ 1. Register\n " +
                        "✎ 2. Login\n " +
                        "✎ 3. Admin Section\n " +
                        "✎ 4. Exit Menu");
    }

    public static void printPatientMenu() {
        System.out.println(
                "――――― Welcome To The Patients Page ――――――\n " +
                        "✎ 1. Add Prescription\n " +
                        "✎ 2. See Confirmed Prescriptions\n " +
                        "✎ 3. Edit Prescription\n " +
                        "✎ 4. Delete Prescription\n " +
                        "✎ 5. Exit Menu");
    }

    public static void printEditPrescriptionMenu() {
        System.out.println(
                "――――― Welcome To The Edit Prescription Page ――――――\n " +
                        "✎ 1. Add Item To Prescription\n " +
                        "✎ 2. Edit The Count Of Items In Each Prescription\n " +
                        "✎ 3. Delete The Item From The Prescription\n " +
                        "✎ 4. Exit Menu");
    }

    public static void printAdminMenu() {
        System.out.println(
                "――――― Welcome To The Admin Page ――――――\n " +
                        "✎ 1. See All Prescription\n " +
                        "✎ 2. Edit Item\n " +
                        "✎ 3. Edit Admin(Only SuperAdmin)\n " +
                        "✎ 4. Exit Menu");
    }

    public static void printEditItemMenu() {
        System.out.println(
                "――――― Welcome To The Edit Item Page ――――――\n " +
                        "✎ 1. Add Item\n " +
                        "✎ 2. Edit Item\n " +
                        "✎ 3. Delete Item\n " +
                        "✎ 4. Exit Menu");
    }

    public static void printEditAdminMenu() {
        System.out.println(
                "――――― Welcome To The Edit Admin Page ――――――\n " +
                        "✎ 1. Add Admin\n " +
                        "✎ 2. Edit Admin\n " +
                        "✎ 3. Delete Admin\n " +
                        "✎ 4. Exit Menu");
    }

    public static void register(MainService mainService){
        System.out.print("Enter your full name: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter your 10-digit national code: ");
        String nationalCode = scanner.next();
        scanner.nextLine();

        Patient patient = new Patient(name, username, password, age, nationalCode);
        mainService.register(patient);
    }

    public static void login(MainService mainService){
        System.out.print("Enter your username: ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter your 10-digit national code: ");
        String nationalCode = scanner.next();
        scanner.nextLine();

        boolean flag = true;

        if (mainService.loginPatient(username, password, nationalCode)){
            while (flag){
                printPatientMenu();

                System.out.print("Choose an option: ");
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input){
                    case 1:
                        addPrescription(mainService);
                        break;
                    case 2:
                        seeConfirmedPrescription(mainService);
                        break;
                    case 3:
                        editPrescription(mainService);
                        break;
                    case 4:
                        deletePrescription(mainService);
                        break;
                    case 5:
                        flag = false;
                        break;
                    default:
                        System.out.println("You entered the wrong option!");
                        break;
                }
            }
        }
        else {
            System.out.println("Patient with this username or nationalCode was not found. Please register");
        }
    }

    public static void addPrescription(MainService mainService){
        int prescriptionId = mainService.createPrescription();

        boolean flag = true;
        while (flag){
            mainService.showAllItem();

            System.out.print("Please enter the desired item ID: ");
            int itemId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the desired number of items (one or more): ");
            int countItem = scanner.nextInt();
            scanner.nextLine();

            mainService.addItemToPrescription(prescriptionId, itemId, countItem);

            System.out.print("If you don't want to enter another item, enter 0 to exit and enter 1 to continue: ");
            int exit = scanner.nextInt();
            scanner.nextLine();

            if (exit == 0){
                flag = false;
            }
        }
    }

    public static void seeConfirmedPrescription(MainService mainService){
        if (mainService.showConfirmedPrescription()){
            System.out.print("Enter its ID to view prescription details: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine();

            mainService.showPrescriptionDetail(prescriptionId);
        }
        else {
            System.out.println("You have no confirmed prescription");
        }
    }

    public static void editPrescription(MainService mainService){
        boolean flag = true;

        while (flag){

            printEditPrescriptionMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input){
                case 1:
                    addItemToPrescription(mainService);
                    break;
                case 2:
                    changeCountItem(mainService);
                    break;
                case 3:
                    deleteItemFromPrescription(mainService);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void addItemToPrescription(MainService mainService){
        if (mainService.showAllPrescription()){
            System.out.print("Enter the ID of the prescription you want to edit: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine();

            mainService.showAllItem();

            System.out.print("Enter the ID of the item you want to add: ");
            int itemId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the desired count of items: ");
            int countItem = scanner.nextInt();
            scanner.nextLine();

            mainService.addItemToPrescription(prescriptionId, itemId, countItem);

            mainService.updatePrescriptionConfirmed(prescriptionId);
        }
        else {
            System.out.println("You have no prescription");
        }
    }

    public static void changeCountItem(MainService mainService){
        if (mainService.showAllPrescription()){
            System.out.print("Enter the ID of the prescription you want to edit: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine();

            mainService.showPrescriptionDetail(prescriptionId);

            System.out.print("Enter the ID of the item you want to change the count of: ");
            int itemId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter the new count: ");
            int countItem = scanner.nextInt();
            scanner.nextLine();

            mainService.changeCountItem(countItem, prescriptionId, itemId);

            mainService.updatePrescriptionConfirmed(prescriptionId);
        }
        else {
            System.out.println("You have no prescription");
        }
    }

    public static void deleteItemFromPrescription(MainService mainService){
        if (mainService.showAllPrescription()){
            System.out.print("Enter the ID of the prescription you want to edit: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine();

            mainService.showPrescriptionDetail(prescriptionId);

            System.out.print("Enter the ID of the item you want to delete: ");
            int itemId = scanner.nextInt();
            scanner.nextLine();

            mainService.deleteAnItemFromPrescription(prescriptionId, itemId);

            mainService.updatePrescriptionConfirmed(prescriptionId);
        }
        else {
            System.out.println("You have no prescription");
        }
    }

    public static void deletePrescription(MainService mainService){
        if (mainService.showAllPrescription()){
            System.out.print("Enter the ID of the prescription you want to delete: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine();

            mainService.deletePrescription(prescriptionId);

            mainService.deleteAllItem(prescriptionId);
        }
        else {
            System.out.println("You have no prescription");
        }
    }

    public static void admin(MainService mainService){
        System.out.print("Enter your username: ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter your 10-digit national code: ");
        String nationalCode = scanner.next();
        scanner.nextLine();

        boolean flag = true;

        if (mainService.loginAdmin(username, password, nationalCode)) {
            while (flag) {
                printAdminMenu();

                System.out.print("Choose an option: ");
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        showAllPrescriptionForAdmin(mainService);
                        break;
                    case 2:
                        editItem(mainService);
                        break;
                    case 3:
                        editAdmin(mainService);
                        break;
                    case 4:
                        flag = false;
                        break;
                    default:
                        System.out.println("You entered the wrong option!");
                        break;
                }
            }
        }
        else {
            System.out.println("Admin with this username or nationalCode was not found. Please register");
        }
    }

    public static void showAllPrescriptionForAdmin(MainService mainService){
        if (mainService.showAllNotConfirmedPrescription()){
            System.out.print("Enter the ID of the prescription you want to confirmed: ");
            int prescriptionId = scanner.nextInt();
            scanner.nextLine();

            mainService.showPrescriptionDetail(prescriptionId);

            System.out.print("Enter the total price: ");
            int totalPrice = scanner.nextInt();
            scanner.nextLine();

            mainService.setPrescriptionTotalPrice(totalPrice, prescriptionId);
        }
        else {
            System.out.println("There are no unconfirmed prescription");
        }
    }

    public static void editItem(MainService mainService){
        boolean flag = true;

        while (flag){
            printEditItemMenu();

            System.out.print("Choose an option: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input){
                case 1:
                    addItem(mainService);
                    break;
                case 2:
                    editItemByAdmin(mainService);
                    break;
                case 3:
                    deleteItemByAdmin(mainService);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("You entered the wrong option!");
                    break;
            }
        }
    }

    public static void addItem(MainService mainService){
        System.out.print("Enter the name of the item: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter the item's inventory (true or false): ");
        String isExist = scanner.next();
        scanner.nextLine();

        System.out.print("Enter the price of the item: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        Item item = new Item(name, Boolean.parseBoolean(isExist), price);
        mainService.addItem(item);
    }

    public static void editItemByAdmin(MainService mainService){
        mainService.showAllItemForAdmin();

        System.out.print("Enter the ID of the item you want to edit: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the name of the item: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter the item's inventory (true or false): ");
        String isExist = scanner.next();
        scanner.nextLine();

        System.out.print("Enter the price of the item: ");
        int price = scanner.nextInt();
        scanner.nextLine();

        Item item = new Item(itemId, name, Boolean.parseBoolean(isExist), price);
        mainService.editItem(item);
    }

    public static void deleteItemByAdmin(MainService mainService){
        mainService.showAllItemForAdmin();

        System.out.print("Enter the ID of the item you want to delete: ");
        int itemId = scanner.nextInt();
        scanner.nextLine();

        Item item = new Item(itemId);
        mainService.deleteItem(item);
    }

    public static void editAdmin(MainService mainService){
        if (mainService.loginSuperAdmin()){
            boolean flag = true;

            while (flag){
                printEditAdminMenu();

                System.out.print("Choose an option: ");
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input){
                    case 1:
                        addAdmin(mainService);
                        break;
                    case 2:
                        editAdminBySuperAdmin(mainService);
                        break;
                    case 3:
                        deleteAdmin(mainService);
                        break;
                    case 4:
                        flag = false;
                        break;
                    default:
                        System.out.println("You entered the wrong option!");
                        break;
                }
            }
        }
    }

    public static void addAdmin(MainService mainService){
        System.out.print("Enter admin full name: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter admin username: ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter admin age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter admin 10-digit national code: ");
        String nationalCode = scanner.next();
        scanner.nextLine();

        System.out.print("Enter admin role");
        String role = scanner.next();
        scanner.nextLine();

        Admin admin = new Admin(name, username, password, age, nationalCode, role);
        mainService.addAdmin(admin);
    }

    public static void editAdminBySuperAdmin(MainService mainService){
        mainService.showAllAdmin();

        System.out.print("Enter the ID of the admin you want to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter admin full name: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter admin username: ");
        String username = scanner.next();
        scanner.nextLine();

        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter admin age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter admin 10-digit national code: ");
        String nationalCode = scanner.next();
        scanner.nextLine();

        System.out.print("Enter admin role");
        String role = scanner.next();
        scanner.nextLine();

        Admin admin = new Admin(id, name, username, password, age, nationalCode, role);
        mainService.editAdmin(admin);
    }

    public static void deleteAdmin(MainService mainService){
        mainService.showAllAdmin();

        System.out.print("Enter the ID of the admin you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Admin admin = new Admin(id);
        mainService.deleteAdmin(admin);
    }
}
