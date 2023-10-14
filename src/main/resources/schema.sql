DROP DATABASE IF EXISTS bookstore;
CREATE DATABASE bookstore;
USE bookstore;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS appuser;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS category;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE appuser
(
userid BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(100) NOT NULL,
passwordhash VARCHAR(255) NOT NULL,
email VARCHAR(255),
role VARCHAR(100) NOT NULL	
);

INSERT INTO appuser (username, passwordhash, email, role)
VALUES ("user", "$2a$10$frMxi0uQMwPkoCW8PB0f.e9vRXvASV.D/cdzFjHbp3NBTkl235R0q", "user@user.com", "USER"),
("admin", "$2a$10$ks3hdKMUX6LZzgBWxte27.IVYiboBl47B3ghUaJi7g9SWmiqe2EUS", "admin@admin.com", "ADMIN");

CREATE TABLE category
(
categoryid BIGINT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100)
);

INSERT INTO category (name)
VALUES ("Action and Adventure"),
("Satire"),
("Romance"),
("Classics");

CREATE TABLE book
(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(100),
author VARCHAR(255),
publicationyear INT,
isbn VARCHAR(30),
price DOUBLE,
categoryid BIGINT,
FOREIGN KEY (categoryid) REFERENCES category(categoryid)
);

INSERT INTO book (title, author, publicationyear, isbn, price, categoryid)
VALUES ("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 19.90, 3),
("Animal Farm", "George Orwell", 1945, "2212343-51", 14.95, 2),
("To Kill a Mockinbird", "Harper Lee", 1960, "951-864-028-9", 15.90, 4);

SELECT * FROM appuser;
SELECT * FROM book;
SELECT * FROM category;


