package com.codecool.shop.dao.implementation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <h1>Init Test Shop</h1>
 * Initializes a new database connection for testing
 * <p>
 * responsible for create connection with the database testshop for test methods
 * </p>
 *
 * @author  Codewalkers
 * @since   2017-05
 */
public class InitTestShop {

    /**
     * Database URL for JDBC connection
     */
    public String DATABASE = "jdbc:postgresql://localhost:5432/testshop";

    /**
     * Database credential - DB username - from config file
     */
    private final String DB_USER = readConfigFile().get(0);

    /**
     * Database credential - DB password - from config file
     */
    private final String DB_PASSWORD = readConfigFile().get(1);

    /**
     * Drops old and creates new table in database testshop
     */
    public void initDbForTestshop() {
        String query =
                "\n" +
                        "DROP TABLE if EXISTS product, product_category,supplier;\n" +
                        "\n" +
                        "\n" +
                        "CREATE TABLE product_category\n" +
                        "(\n" +
                        "  id INTEGER PRIMARY KEY,\n" +
                        "  name varchar(40),\n" +
                        "  description varchar(50),\n" +
                        "  department VARCHAR(40)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE supplier\n" +
                        "(\n" +
                        "  id INTEGER PRIMARY KEY,\n" +
                        "  name varchar(40),\n" +
                        "  description varchar(50)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE product\n" +
                        "(\n" +
                        "  id INTEGER PRIMARY KEY,\n" +
                        "  name varchar(40),\n" +
                        "  description varchar(150),\n" +
                        "  default_price DOUBLE PRECISION,\n" +
                        "  default_currency varchar(3),\n" +
                        "  product_category INTEGER REFERENCES product_category(id),\n" +
                        "  supplier INTEGER REFERENCES supplier(id)\n" +
                        ");\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n";
        executeQuery(query);
    }

    /**
     * Reads config data from txt file
     *
     * @return List of lines of the config file
     * @exception IOException if config file not found
     */
    private List<String> readConfigFile() {
        try {
            return Files.readAllLines(Paths.get("src/dbConfig.txt"), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Config file not found");
        }
        return null;
    }

    /**
     * Creates connection with postgreSQL database
     *
     * @return Connection object
     * @exception SQLException if connecting is failed
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    /**
     * Transmits the created query object to the database for execution
     *
     * @param query SQL query to be executed
     * @exception SQLException if connecting is failed - catches the exception
     */
    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
