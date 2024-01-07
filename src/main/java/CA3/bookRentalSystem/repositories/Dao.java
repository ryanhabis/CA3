package CA3.bookRentalSystem.repositories;
/**
 * @author: Heidi
 * @author: Evan (Small Changes)
 * Reference: Michelle's notes
 **/

import CA3.bookRentalSystem.exceptions.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

    //REFERENCE: Michelle's Class Notes/Examples

    private Connection conn = null;
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

        //Checking if conn is null
        if (conn == null) {
            //Set driver info, url, username and password
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/" + databaseName;
            String username = "root";
            String password = "";
            try {
                //try to connect to the driver using these details
                Class.forName(driver);
                conn = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException classNFEx) {
                System.out.println("Failed to find driver class " + classNFEx.getMessage());
                System.exit(1);
            } catch (SQLException sqlEx) {
                System.out.println("Connection failed " + sqlEx.getMessage());
                System.exit(2);
            }
        }
        return conn;
    }

    /**
     * Method to close connection to Database. Will catch SQL Exception if method fails.
     * @param conn - the connection to be closed.
     * @throws DaoException - the exception thrown if there is a problem regarding the dao.
     */
    public void freeConnection(Connection conn) throws DaoException {
        try {
            //if there is a connection then close it and set to null
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            //if an sql exception occurs, print the message generated
            System.out.println("Failed to free connection: " + e.getMessage());
            //set exit status to 1
            System.exit(1);
        }
    }

}
