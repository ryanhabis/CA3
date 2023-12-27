/**
 * @author: Evan
 * Reference: Michelle's notes
 **/

package com.example.CA3.rental;

import java.util.Objects;

public class Book {

    //Fields
    private int bookId;
    private int genreId;
    private String title;
    private String description;
    private String author;
    private int quantityInStock;
    private double bookPrice;

    //Constructors
    public Book() {
    }

    public Book(int bookId, int genreId, String title, String description, String author, int quantityInStock, double bookPrice) {
        this.bookId = bookId;
        this.genreId = genreId;
        this.title = title;
        this.description = description;
        this.author = author;
        this.quantityInStock = quantityInStock;
        this.bookPrice = bookPrice;
    }

    //Getters
    public int getBookId() {
        return bookId;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    //Setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    //equals Method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId;
    }

    //hashCode Method
    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    //toString Method
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", genreId=" + genreId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", quantityInStock=" + quantityInStock +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
