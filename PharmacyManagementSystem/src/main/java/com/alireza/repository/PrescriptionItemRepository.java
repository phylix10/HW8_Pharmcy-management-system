package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Item;
import com.alireza.model.Prescription;
import com.alireza.model.PrescriptionItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionItemRepository {
    private static final String ADD_ITEM_TO_PRESCRIPTION_QUERY = "insert into prescription_item(prescription_id,item_id,count_item) values (?,?,?)";
    private static final String SELECT_PRESCRIPTION_ITEM_QUERY = "select * from prescription_item pi join item i on i.id = pi.item_id where pi.prescription_id = ?";
    private static final String UPDATE_PRESCRIPTION_ITEM_COUNT_QUERY = "update prescription_item set count_item = ? where prescription_id = ? and item_id = ?";
    private static final String DELETE_ITEM_FROM_PRESCRIPTION_QUERY = "delete from prescription_item where prescription_id = ? and item_id = ?";
    private static final String DELETE_ALL_ITEM_FROM_PRESCRIPTION_QUERY = "delete from prescription_item where prescription_id = ?";

    public static void addItemToPrescription(int prescriptionId, int itemId, int countItem) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ITEM_TO_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1, prescriptionId);
            preparedStatement.setInt(2, itemId);
            preparedStatement.setInt(3, countItem);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
    public static List<PrescriptionItem> showDetailPrescription(int prescriptionId){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRESCRIPTION_ITEM_QUERY);
            preparedStatement.setInt(1, prescriptionId);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<PrescriptionItem> prescriptionItemList = new ArrayList<>();
            while (resultSet.next()) {
                PrescriptionItem newPrescriptionItem = new PrescriptionItem();
                newPrescriptionItem.setId(resultSet.getInt(1));
                newPrescriptionItem.setPrescriptionId(new Prescription(resultSet.getInt(2)));
                newPrescriptionItem.setItemId(new Item(
                        resultSet.getInt(5),
                        resultSet.getString(6),
                        resultSet.getBoolean(7),
                        resultSet.getInt(8)));
                newPrescriptionItem.setCountItem(resultSet.getInt(4));
                prescriptionItemList.add(newPrescriptionItem);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return prescriptionItemList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }

    public static void updateCountItem(int countItem, int prescriptionId, int itemId) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRESCRIPTION_ITEM_COUNT_QUERY);
            preparedStatement.setInt(1, countItem);
            preparedStatement.setInt(2, prescriptionId);
            preparedStatement.setInt(3, itemId);


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void deleteItemFromPrescription(int prescriptionId, int itemId) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_FROM_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1, prescriptionId);
            preparedStatement.setInt(2, itemId);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void deleteAllItemFromPrescription(int prescriptionId) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_ITEM_FROM_PRESCRIPTION_QUERY);
            preparedStatement.setInt(1, prescriptionId);

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }
}
