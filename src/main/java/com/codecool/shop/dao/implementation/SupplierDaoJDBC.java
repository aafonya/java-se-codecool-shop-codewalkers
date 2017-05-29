package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by judit on 17.05.17.
 */
public class SupplierDaoJDBC implements SupplierDao {

    /**
     * Database URL for JDBC connection
     */
     static String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";

    /**
     * Database credential - DB username - from config file
     */
    private static final String DB_USER = readConfigFile().get(0);

    /**
     * Database credential - DB password - from config file
     */
    private static final String DB_PASSWORD = readConfigFile().get(1);


    private static SupplierDaoJDBC instance = null;

    /**
     * Reads config data from txt file
     *
     * @return List of lines of the config file
     * @exception IOException if config file not found
     */
    private static List<String> readConfigFile() {
        try {
            return Files.readAllLines(Paths.get("src/dbConfig.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Config file not found");
        }
        return null;
    }

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {

            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO supplier (id, name, description)\n " +
                "VALUES ('" + supplier.getId() + "','" + supplier.getName() + "', '" + supplier.getDescription() +  "');";
        executeQuery(query);
    }

    @Override
    public Supplier find(int id) {

        String query = "SELECT * FROM supplier WHERE id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                return instantiateSupplierFromQuery(resultSet);
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM supplier WHERE id = '" + id +"';";
        executeQuery(query);
    }

    @Override
    public List<Supplier> getAll() {

        String query = "SELECT * FROM supplier;";

        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            while (resultSet.next()){
                Supplier actSupplier = instantiateSupplierFromQuery(resultSet);
                resultList.add(actSupplier);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public Supplier instantiateSupplierFromQuery(ResultSet resultSet) throws SQLException {
        Supplier result = new Supplier(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"));
        return result;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Supplier newSupplier1 = new Supplier("supplier", "dep");
        Supplier newSupplier2 = new Supplier("newsupplier2", "dep2");
        SupplierDaoJDBC SupplierDaoJdbc = SupplierDaoJDBC.getInstance();
        SupplierDaoJdbc.add(newSupplier1);
        SupplierDaoJdbc.add(newSupplier2);

        System.out.println(newSupplier1.getId());
        System.out.println(newSupplier2.getId());
        System.out.println(SupplierDaoJdbc.getAll());
    }
}
