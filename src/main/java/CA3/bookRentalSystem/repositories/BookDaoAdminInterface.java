package CA3.bookRentalSystem.repositories;


import CA3.bookRentalSystem.rental.Book;

import java.util.ArrayList;

public interface BookDaoAdminInterface extends BookDaoInterface {

    public int addBook(int bookId, int genreId, String title, String description, String author, int quantityInstock, double bookPrice);
    //Return new stock number + message using bookId and quantity
    public int updateStock(int bookId, int quantityInStock);
}
