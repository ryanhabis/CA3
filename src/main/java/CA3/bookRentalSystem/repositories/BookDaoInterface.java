package CA3.bookRentalSystem.repositories;



import CA3.bookRentalSystem.rental.Book;

import java.util.ArrayList;

public interface BookDaoInterface {

    /**
     * Gets all books from the database
     * @return ArrayList of Books from the database
     */
    public ArrayList<Book> getAllBooks();

    /**
     * Gets Book IDs by the Title
     * @param title Title of the book being searched
     * @return Arraylist with the IDs of the books matching the title
     */
    public ArrayList<Integer> getBookIdsByTitle(String title);

    /**
     * Checks the stock of the book
     * @param bookId The ID to be checked
     * @return the quantityInStock from the database for the book, -1 if book is not found
     */
    public int checkStock(int bookId);

    /**
     * Gets a book from the database using the ID
     * @param bookId The ID of the book
     * @return the corresponding book to the ID, null if the book isn't found
     */
    public Book getBookByBookId(int bookId);

    /**
     * Gets books from the database using the title
     * @param title The title of the books
     * @return Arraylist of Books to return from the database
     */
    public ArrayList<Book> getBooksByTitle(String title);

    /**
     * Reserves the book if it's in stock
     * @param b The book to reserve
     * @return true if successful, false if not
     */
    public boolean reserveCopy (Book b);

    /**
     * Returns a book and updates the stock
     * @param bookId The ID of the book
     * @param userId The ID of the user
     * @return true if successful, false if not
     */
    public boolean returnBook (int bookId, int userId);
}
