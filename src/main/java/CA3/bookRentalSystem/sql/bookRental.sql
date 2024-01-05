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
                       userId INT AUTO_INCREMENT NOT NULL,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       firstName VARCHAR(50) NOT NULL,
                       lastName VARCHAR(50) NOT NULL,
                       dob DATE NOT NULL,
                       phoneNumber VARCHAR(15) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       addressLine1 VARCHAR (50) NOT NULL,
                       addressLine2 VARCHAR (50),
                       city VARCHAR(50) NOT NULL,
                       county VARCHAR(50) NOT NULL,
                       eircode VARCHAR(10) NOT NULL,
                       accountStatus enum ('enabled', 'disabled'), /*this added*/
                       userType enum ('admin', 'customer') NOT NULL,
                       constraint pk_users primary key(userId)
);


drop table if exists genres;

CREATE TABLE genres (
                        genreId int(2) AUTO_INCREMENT NOT NULL,
                        genreName varchar(50) UNIQUE NOT NULL,
                        constraint pk_genres primary key (genreId)
);

drop table if exists books;
SET FOREIGN_KEY_CHECKS = 0;
CREATE TABLE books (
                       bookId int(20) AUTO_INCREMENT NOT NULL,
                       genreId int(2) NOT NULL,
                       title varchar(100) NOT NULL,
                       description text,
                       author varchar(80),
                       quantityInStock int(11) NOT NULL,
                       bookPrice decimal(10,2) NOT NULL,
                       constraint pk_books primary key (bookId),
                       constraint fk_books_genres foreign key (genreId) references genres(genreId)
);

drop table if exists loans;

    /*no fk constraints*/
CREATE TABLE loans (
                       loanId int(20) AUTO_INCREMENT NOT NULL,
                       bookId int(20) NOT NULL,
                       userId int(2) NOT NULL,
                       loanStartDate date NOT NULL,
                       loanDueDate date NOT NULL,
                       overdueFee decimal(10,2), /*do we need this here? what else can we get rid of?*/
                       dateReturned date,
                       constraint pk_loans primary key (loanId)
);


CREATE TABLE fees
(
    LoanId int(20) AUTO_INCREMENT NOT NULL,
    fee decimal(10,2) NOT NULL,
    paidStatus enum ('paid', 'unpaid') NOT NULL,
    constraint pk_fees primary key (loanId)
)


