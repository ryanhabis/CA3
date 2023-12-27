package com.example.CA3.repositories;

import com.example.CA3.rental.Book;

public interface BookDaoAdminInterface extends BookDaoInterface {

    public int addBook(int bookId, int genreId, String title, String description, String author, int quantityInstock, double bookPrice);
    //Return new stock number + message using bookId and quantity
    public int updateStock(int bookId, int quantityInStock);
    //Return current stock number using bookId
    public int checkStock(int bookId);
    //Return book using bookId
    public Book getBookByBookId(int bookId);
    //Return book using title
    public Book getBookByBookTitle(String title);

    public boolean reserveCopy (Book b);
}
