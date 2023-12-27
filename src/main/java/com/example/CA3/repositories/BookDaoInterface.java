package com.example.CA3.repositories;

import com.example.CA3.rental.Book;

import java.util.ArrayList;

public interface BookDaoInterface {
    //Customer Methods
    //Return all books
    public ArrayList<Book> getAllBooks();
    //Return bookId using Title
    public int getBookIdByTitle(String title);
}
