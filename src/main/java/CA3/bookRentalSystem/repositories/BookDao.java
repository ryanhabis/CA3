package CA3.bookRentalSystem.repositories;

import CA3.bookRentalSystem.exceptions.DaoException;
import CA3.bookRentalSystem.rental.Book;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BookDao extends Dao implements BookDaoInterface, BookDaoAdminInterface {

        public BookDao(Connection conn) {
            super(conn);
        }

        public BookDao(String databaseName) {
            super(databaseName);
        }

        /**
         * Gets all books from the database
         * @return ArrayList of Books from the database
         */
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

            } catch (SQLException e) {
                System.out.println("SQL exception: " +e.getMessage());
            } catch (DaoException e) {
                System.out.println("Dao exception: " +e.getMessage());
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

        /**
         * Gets Book IDs by the Title
         * @param title Title of the book being searched
         * @return Arraylist with the IDs of the books matching the title
         */
        @Override
        public ArrayList<Integer> getBookIdsByTitle(String title) {

            ArrayList<Integer> bookIds = new ArrayList<>();
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                //Getting connection
                conn = getConnection();
                //Creating query
                String query = "select bookId from books where title = ?";
                //Prepare & execute query
                ps = conn.prepareStatement(query);
                ps.setString(1, title);
                rs = ps.executeQuery();

                while (rs.next()) {
                    bookIds.add(rs.getInt("bookId"));
                }

            } catch (SQLException e) {
                System.out.println("SQL exception: " +e.getMessage());
            } catch (DaoException e) {
                System.out.println("Dao exception: " +e.getMessage());
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

            return bookIds;
        }

    /**
     * Adds a new book to the database
     * @param bookId ID of the new book
     * @param genreId ID of the genre of the book
     * @param title Title of the book
     * @param description Description of the book
     * @param author Author of the book
     * @param quantityInStock The quantity of the book in stock
     * @param bookPrice The price of the book
     * @return the number of rows added in the database, should be 1 if it runs successfully
     */
    @Override
    public int addBook(int bookId, int genreId, String title, String description, String author, int quantityInStock, double bookPrice) {

        int rowsadded = 0;
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "insert into books values(?, ?, ?, ?, ?, ?, ?)";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookId);
            ps.setInt(2, genreId);
            ps.setString(3, title);
            ps.setString(4, description);
            ps.setString(5, author);
            ps.setInt(6, quantityInStock);
            ps.setDouble(7, bookPrice);
            rowsadded = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
        }

        //Closing Connection
        finally {
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
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return rowsadded;
    }

    /**
     * Updates the stock of the book
     * @param bookId The ID of the book
     * @param quantityInStock The new stock
     * @return the number of rows changed in the database, should be 1 if it runs successfully
     */
    @Override
    public int updateStock(int bookId, int quantityInStock) {

        int rowschanged = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "update books set quantityInStock = ? where bookId = ? and quantityInStock + ? >= 0";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            ps.setInt(1, quantityInStock);
            ps.setInt(2, bookId);
            ps.setInt(3, quantityInStock);

            rowschanged = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
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
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return rowschanged;
    }

    /**
     * Checks the stock of the book
     * @param bookId The ID to be checked
     * @return the quantityInStock from the database for the book, -1 if book is not found
     */
    @Override
    public int checkStock(int bookId) {

        //Will return -1 if a book is not found
        int stock = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "select quantityInStock from books where bookId = ?";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            if (rs.next()) {
                stock = rs.getInt("quantityInStock");
            }

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
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
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return stock;
    }

    /**
     * Gets a book from the database using the ID
     * @param bookId The ID of the book
     * @return the corresponding book to the ID, null if the book isn't found
     */
    @Override
    public Book getBookByBookId(int bookId) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Creating book
        Book b = new Book();

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "select * from books where bookId = ?";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookId);
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

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
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
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return b;
    }

    /**
     * Gets books from the database using the title
     * @param title The title of the books
     * @return Arraylist of Books to return from the database
     */
    @Override
    public ArrayList<Book> getBooksByTitle(String title) {

        ArrayList<Book> books = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "select * from books where title = ?";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();

            while (rs.next()) {

                //Creating book
                Book b = new Book();
                //Setting values to correct columns
                b.setBookId(rs.getInt("bookId"));
                b.setGenreId(rs.getInt("genreId"));
                b.setTitle(rs.getString("title"));
                b.setDescription(rs.getString("description"));
                b.setAuthor(rs.getString("author"));
                b.setQuantityInStock(rs.getInt("quantityInStock"));
                b.setBookPrice(rs.getDouble("bookPrice"));

            }

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
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
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return books;
    }

    /**
     * Reserves the book if it's in stock
     * @param b The book to reserve
     * @return true if successful, false if not
     */
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

        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
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

        return b.getBookId() != -1;
    }

    /**
     * Returns a book and updates the stock
     * @param bookId The ID of the book
     * @return true if successful, false if not
     */
    @Override
    public boolean returnBook(int bookId) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //Getting connection
            conn = getConnection();
            //Creating query
            String query = "update books set quantityInStock = quantityInStock + 1 where bookId = ? ";
            //Prepare & execute query
            ps = conn.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();


        } catch (SQLException e) {
            System.out.println("SQL exception: " +e.getMessage());
            return false;
        } catch (DaoException e) {
            System.out.println("Dao exception: " +e.getMessage());
            return false;
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
                    System.out.println("Dao exception caught: " + e.getMessage());
                }
            }
        }
        return true;
    }

}