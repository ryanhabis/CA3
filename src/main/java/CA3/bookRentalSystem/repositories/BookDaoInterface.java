package CA3.bookRentalSystem.repositories;



import CA3.bookRentalSystem.rental.Book;

import java.util.ArrayList;

public interface BookDaoInterface {
    //Customer Methods
    //Return all books
    public ArrayList<Book> getAllBooks();
    //Return bookId using Title
    public int getBookIdByTitle(String title);
}
