package CA3.bookRentalSystem.repositories;


import CA3.bookRentalSystem.rental.Book;

import java.util.ArrayList;

public interface BookDaoAdminInterface extends BookDaoInterface {

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
    public int addBook(int bookId, int genreId, String title, String description, String author, int quantityInStock, double bookPrice);

    /**
     * Updates the stock of the book
     * @param bookId The ID of the book
     * @param quantityInStock The new stock
     * @return the number of rows changed in the database, should be 1 if it runs successfully
     */
    public int updateStock(int bookId, int quantityInStock);
}
