package CA3.bookRentalSystem.repositories;



import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDaoAdmin extends Dao implements BookDaoInterface {

    public BookDaoAdmin(Connection conn) {
        super(conn);
    }

    public BookDaoAdmin(String databaseName) {
        super(databaseName);
    }

    @Override
    public ArrayList<Book> getAllBooks() {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Creating ArrayList
        ArrayList<Book> books = new ArrayList<>();

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "select * from books";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()) {
                //Creating Book
                Book b = new Book();

                //Setting values to correct columns
                b.setBookId(rs.getInt("bookId"));
                b.setGenreId(rs.getInt("genreId"));
                b.setTitle(rs.getString("title"));
                b.setDescription(rs.getString("description"));
                b.setAuthor(rs.getString("author"));
                b.setQuantityInStock(rs.getInt("quantityInStock"));
                b.setBookPrice(rs.getDouble("bookPrice"));

                //Adding book to ArrayList
                books.add(b);
            }

        } catch (SQLException | DaoException e) {
            System.out.println("SQL exception: " +e.getMessage());
        }

        //Closing Connection
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Issue when closing result set: ");
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Issue when closing prepared statement: ");
                }
            }
            if(conn != null) {
                try {
                    freeConnection(conn);
                } catch (DaoException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return books;
    }

    @Override
    public int getBookIdByTitle(String title) {
        return 0;
    }


    // BookDao to reserve a copy (if there are no copies available, return a fail code indicating a loan could not be made)
    public boolean reserveCopy (Book b) {

        //check book has enough stock
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "select * from books where bookId = ? and quantityInStock > 0 ";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            int id = b.getBookId();
            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {

                //Setting values to correct columns
                b.setBookId(rs.getInt("bookId"));
                b.setGenreId(rs.getInt("genreId"));
                b.setTitle(rs.getString("title"));
                b.setDescription(rs.getString("description"));
                b.setAuthor(rs.getString("author"));
                b.setQuantityInStock(rs.getInt("quantityInStock"));
                b.setBookPrice(rs.getDouble("bookPrice"));

            }

            return b.getBookId() != -1;

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        }

        //Closing Connection
        finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Issue when closing result set: ");
                }
            }
            if(ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Exception message: " + e.getMessage());
                    System.out.println("Issue when closing prepared statement: ");
                }
            }
            if(conn != null) {
                try {
                    freeConnection(conn);
                } catch (DaoException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return false;
    }



}
