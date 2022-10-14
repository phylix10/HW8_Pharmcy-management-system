package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Admin;
import com.alireza.model.enumeration.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {
    private static final String ADD_ADMIN_QUERY =
            "insert into admin (name,username,password,age,national_code,role) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_ADMIN_QUERY = "update admin set name = ? , username = ? , password = ?, age = ? , " +
            "national_code = ?, role = ? where id = ?";
    private static final String SELECT_ADMIN_QUERY = "select * from admin where username = ? and national_code = ?";
    private static final String SELECT_SUPER_ADMIN_QUERY = "select * from admin where username = ? and role = 'SUPER_ADMIN'";
    private static final String DELETE_ADMIN_BY_ID_QUERY = "delete from admin where id = ?";
    private static final String SELECT_ALL_ADMIN = "select * from admin";

    public static void createAdmin(Admin admin) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ADMIN_QUERY);
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getUsername());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setInt(4, admin.getAge());
            preparedStatement.setString(5, admin.getNationalCode());
            preparedStatement.setString(6, String.valueOf(admin.getRole()));

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void updateAdmin(Admin admin) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_QUERY);
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getUsername());
            preparedStatement.setString(3, admin.getPassword());
            preparedStatement.setInt(4, admin.getAge());
            preparedStatement.setString(5, admin.getNationalCode());
            preparedStatement.setString(6, String.valueOf(admin.getRole()));
            preparedStatement.setInt(7, admin.getId());


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static Admin findAdmin(String username, String nationalCode) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, nationalCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Admin newAdmin = new Admin();
                newAdmin.setId(resultSet.getInt("id"));
                newAdmin.setUsername(resultSet.getString("username"));
                newAdmin.setPassword(resultSet.getString("password"));
                newAdmin.setAge(resultSet.getInt("age"));
                newAdmin.setNationalCode(resultSet.getString("national_code"));
                newAdmin.setRole(Roles.valueOf(resultSet.getString("role")));
                return newAdmin;
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

    public static Admin findSuperAdmin(String username) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPER_ADMIN_QUERY);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Admin newAdmin = new Admin();
                newAdmin.setId(resultSet.getInt("id"));
                newAdmin.setName(resultSet.getString("name"));
                newAdmin.setUsername(resultSet.getString("username"));
                newAdmin.setPassword(resultSet.getString("password"));
                newAdmin.setAge(resultSet.getInt("age"));
                newAdmin.setNationalCode(resultSet.getString("national_code"));
                newAdmin.setRole(Roles.valueOf(resultSet.getString("role")));
                return newAdmin;
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

    public static void deleteAdminById(Admin admin) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_BY_ID_QUERY);
            preparedStatement.setInt(1, admin.getId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static List<Admin> showAllAdmin(){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMIN);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Admin> adminList = new ArrayList<>();
            while (resultSet.next()) {
                Admin newAdmin = new Admin();
                newAdmin.setId(resultSet.getInt("id"));
                newAdmin.setName(resultSet.getString("name"));
                newAdmin.setUsername(resultSet.getString("username"));
                newAdmin.setPassword(resultSet.getString("password"));
                newAdmin.setAge(resultSet.getInt("age"));
                newAdmin.setNationalCode(resultSet.getString("national_code"));
                newAdmin.setRole(Roles.valueOf(resultSet.getString("role")));
                adminList.add(newAdmin);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return adminList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }
}
