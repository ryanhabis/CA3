package com.example.CA3.repositories;

import com.example.CA3.exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    //REFERENCE: Michelle's Class Notes/Examples

    private Connection conn;
    private String databaseName;
    public Dao(Connection conn){ this.conn = conn; } //super connection (we use fake connections for mocking)
    public Dao(String databaseName){
        this.databaseName = databaseName;
    }

    /**
     * Method to connect to the database by using correct driver, url, username and password.
     * If there is an sql error or driver is not found, Sql Exception or ClassNotFound Exception will be thrown.
     * @return connection - the connection made in the method is returned at the end of the method. If method fails, connection will be null.
     * @throws DaoException - the Dao Exception thrown if there is an error connecting to the Dao.
     */
    public Connection getConnection() throws DaoException {

        //Set driver info, url, username and password
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        String username = "root";
        String password = "";
        Connection connection = null;
        try {
            //try to connect to the driver using these details
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException classNFEx) {
            System.out.println("Failed to find driver class " + classNFEx.getMessage());
            System.exit(1);
        } catch (SQLException sqlEx) {
            System.out.println("Connection failed " + sqlEx.getMessage());
            System.exit(2);
        }
        return connection;
    }

    /**
     * Method to close connection to Database. Will catch SQL Exception if method fails.
     * @param connection - the connection to be closed.
     * @throws DaoException - the exception thrown if there is a problem regarding the dao.
     */
    public void freeConnection(Connection connection) throws DaoException {
        try {
            //if there is a connection then close it and set to null
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            //if an sql exception occurs, print the message generated
            System.out.println("Failed to free connection: " + e.getMessage());
            //set exit status to 1
            System.exit(1);
        }
    }

}
