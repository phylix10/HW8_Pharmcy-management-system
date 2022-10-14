package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientRepository {

    private static final String ADD_PATIENT_QUERY =
            "insert into patient (name,username,password,age,national_code) VALUES (?,?,?,?,?)";
    private static final String SELECT_PATIENT_QUERY = "select * from patient where username = ? and national_code = ?";

    public static void createPatient(Patient patient) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PATIENT_QUERY);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getUsername());
            preparedStatement.setString(3, patient.getPassword());
            preparedStatement.setInt(4, patient.getAge());
            preparedStatement.setString(5, patient.getNationalCode());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static Patient findPatient(String username, String nationalCode) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PATIENT_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, nationalCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Patient newPatient = new Patient();
                newPatient.setId(resultSet.getInt("id"));
                newPatient.setName(resultSet.getString("name"));
                newPatient.setUsername(resultSet.getString("username"));
                newPatient.setPassword(resultSet.getString("password"));
                newPatient.setAge(resultSet.getInt("age"));
                newPatient.setNationalCode(resultSet.getString("national_code"));
                return newPatient;
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }
}
