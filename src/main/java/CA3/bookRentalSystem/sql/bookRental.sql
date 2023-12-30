-- @Author Ryan
-- @author Heidi
-- reference Michelle's class notes
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2023 at 05:05 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


    drop database if exists bookrentalsystem;
         Create database if not exists bookrentalsystem;
                use bookrentalsystem;


drop table if exists users;

SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE users (
                       userId INT AUTO_INCREMENT,
                       firstName VARCHAR(50),
                       lastName VARCHAR(50),
                       username VARCHAR(50) UNIQUE,
                       password VARCHAR(255),
                       dob DATE,
                       phoneNumber VARCHAR(15),
                       email VARCHAR(100) UNIQUE,
                       addressLine1 VARCHAR (50),
                       addressLine2 VARCHAR (50),
                       city VARCHAR(50),
                       county VARCHAR(50),
                       eircode VARCHAR(10),
                       accountStatus enum ('enabled', 'disabled'), /*this added*/
                       userType enum ('admin', 'customer'),
                       constraint pk_users primary key(userId)
);


drop table if exists genres;

CREATE TABLE genres (
                        genreId int(2) AUTO_INCREMENT,
                        genreName varchar(50) UNIQUE,
                        constraint pk_genres primary key (genreId)
);

drop table if exists books;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE books (
                       bookId int(20) AUTO_INCREMENT,
                       genreId int(2),
                       title varchar(100),
                       description text,
                       author varchar(80),
                       quantityInStock int(11),
                       bookPrice decimal(10,2),
                       constraint pk_books primary key (bookId),
                       constraint fk_books_genres foreign key (genreId) references genres(genreId)
);

drop table if exists loans;

    /*no fk constraints*/
CREATE TABLE loans (
                       loanId int(20) AUTO_INCREMENT,
                       bookId int(20),
                       userId int(2),
                       loanStartDate date,
                       loanDueDate date,
                       overdueFee decimal(10,2), /*do we need this here? what else can we get rid of?*/
                       dateReturned date,
                       constraint pk_loans primary key (loanId)
);


CREATE TABLE fees
(
    LoanId int(20) AUTO_INCREMENT,
    fee decimal(10,2),
    paidStatus enum ('paid', 'unpaid'),
    constraint pk_fees primary key (loanId)
)


