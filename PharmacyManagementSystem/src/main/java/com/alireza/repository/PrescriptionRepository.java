package com.alireza.repository;


import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Patient;
import com.alireza.model.Prescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionRepository {
    private static final String ADD_PRESCRIPTION_QUERY = "insert into prescription(patient_id) values (?) returning id";
    private static final String SELECT_CONFIRMED_PRESCRIPTION_QUERY = "select * from prescription where patient_id = ? and is_confirmed = true";
    private static final String SELECT_ALL_PRESCRIPTION_QUERY = "select * from prescription where patient_id = ?";
    private static final String UPDATE_IS_CONFIRMED_PRESCRIPTION_QUERY = "update prescription set is_confirmed = false where id = ? and patient_id = ?";
    private static final String DELETE_PRESCRIPTION_QUERY = "delete from prescription where id = ? and patient_id = ?";
    private static final String SELECT_ALL_IN_CONFIRMED_PRESCRIPTION_QUERY = "select * from prescription where is_confirmed = false";
    private static final String UPDATE_PRESCRIPTION_BY_ADMIN_QUERY = "update prescription set total_price = ?, is_confirmed = true where id = ?";

    public static Integer createPrescription(Patient patient) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1, patient.getId());


            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int prescriptionId = resultSet.getInt("id");

            preparedStatement.close();
            connection.close();

            return prescriptionId;

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Prescription> showConfirmedPrescription(Patient patient){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONFIRMED_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1,patient.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Prescription> prescriptionList = new ArrayList<>();
            while (resultSet.next()) {
                Prescription newPrescription = new Prescription();
                newPrescription.setId(resultSet.getInt("id"));
                newPrescription.setPatientId(new Patient(patient.getName()));
                newPrescription.setConfirmed(resultSet.getBoolean("is_confirmed"));
                newPrescription.setTotalPrice(resultSet.getInt("total_price"));
                prescriptionList.add(newPrescription);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return prescriptionList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Prescription> showAllPrescription(Patient patient){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1,patient.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Prescription> prescriptionList = new ArrayList<>();
            while (resultSet.next()) {
                Prescription newPrescription = new Prescription();
                newPrescription.setId(resultSet.getInt("id"));
                newPrescription.setPatientId(new Patient(patient.getName()));
                newPrescription.setConfirmed(resultSet.getBoolean("is_confirmed"));
                newPrescription.setTotalPrice(resultSet.getInt("total_price"));
                prescriptionList.add(newPrescription);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return prescriptionList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static void updateIsConfirmedPrescription(int prescriptionId, Patient patient) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IS_CONFIRMED_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1, prescriptionId);
            preparedStatement.setInt(2, patient.getId());


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void deletePrescription(int id, Patient patient) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, patient.getId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static List<Prescription> showAllInConfirmedPrescription(){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_IN_CONFIRMED_PRESCRIPTION_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Prescription> prescriptionList = new ArrayList<>();
            while (resultSet.next()) {
                Prescription newPrescription = new Prescription();
                newPrescription.setId(resultSet.getInt("id"));
                newPrescription.setPatientId(new Patient(resultSet.getInt("patient_id")));
                newPrescription.setConfirmed(resultSet.getBoolean("is_confirmed"));
                newPrescription.setTotalPrice(resultSet.getInt("total_price"));
                prescriptionList.add(newPrescription);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return prescriptionList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static void updatePrescriptionByAdmin(int totalPrice, int prescriptionId) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_BY_ADMIN_QUERY);
            preparedStatement.setInt(1, totalPrice);
            preparedStatement.setInt(2, prescriptionId);


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}
