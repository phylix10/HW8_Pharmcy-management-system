package com.alireza.repository;

import com.alireza.configuration.DatabaseConnection;
import com.alireza.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private static final String ADD_ITEM_QUERY =
            "insert into item (name,is_exist,price) VALUES (?,?,?)";
    private static final String UPDATE_ITEM_QUERY = "update item set name = ? , is_exist = ?, price = ? where id = ?";
    private static final String SELECT_ITEM_QUERY = "select * from item where name = ?";
    private static final String DELETE_ITEM_BY_ID_QUERY = "delete from item where id = ?";
    private static final String SELECT_ALL_ITEM_QUERY = "select * from item";

    public static void createItem(Item item) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ITEM_QUERY);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setBoolean(2, item.isExist());
            preparedStatement.setInt(3, item.getPrice());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static void updateItem(Item item) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ITEM_QUERY);
            preparedStatement.setString(1, item.getName());
            preparedStatement.setBoolean(2, item.isExist());
            preparedStatement.setInt(3, item.getPrice());
            preparedStatement.setInt(4, item.getId());


            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static Item findItem(Item item) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ITEM_QUERY);
            preparedStatement.setString(1, item.getName());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Item newItem = new Item();
                newItem.setId(resultSet.getInt("id"));
                newItem.setName(resultSet.getString("name"));
                newItem.setExist(resultSet.getBoolean("is_exist"));
                newItem.setPrice(resultSet.getInt("price"));
                return newItem;
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

    public static void deleteItemById(Item item) {
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ITEM_BY_ID_QUERY);
            preparedStatement.setInt(1, item.getId());

            preparedStatement.execute();

            preparedStatement.close();
            connection.close();

        } catch (SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    public static List<Item> showAllItem(){
        try {
            Connection connection = DatabaseConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEM_QUERY);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Item> itemList = new ArrayList<>();
            while (resultSet.next()) {
                Item newItem = new Item();
                newItem.setId(resultSet.getInt("id"));
                newItem.setName(resultSet.getString("name"));
                newItem.setExist(resultSet.getBoolean("is_exist"));
                newItem.setPrice(resultSet.getInt("price"));
                itemList.add(newItem);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

            return itemList;

        } catch (
                SQLException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
        return null;
    }
}
