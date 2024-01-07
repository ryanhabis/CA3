package CA3.bookRentalSystem.repositories;



import CA3.bookRentalSystem.rental.Book;

import java.util.ArrayList;

public interface BookDaoInterface {
    //Customer Methods
    //Return all books
    public ArrayList<Book> getAllBooks();
    //Return bookId using Title
    public ArrayList<Integer> getBookIdsByTitle(String title);
    //Return current stock number using bookId
    public int checkStock(int bookId);
    //Return book using bookId
    public Book getBookByBookId(int bookId);
    //Return book using title
    public ArrayList<Book> getBooksByTitle(String title);
    public boolean reserveCopy (Book b);
}
