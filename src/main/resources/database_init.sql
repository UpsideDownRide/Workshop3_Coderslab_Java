CREATE DATABASE IF NOT EXISTS WORKSHOP3
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE WORKSHOP3;

CREATE TABLE IF NOT EXISTS users(
  id          INTEGER PRIMARY KEY AUTO_INCREMENT,
  username    VARCHAR(30) NOT NULL,
  email       VARCHAR(100) NOT NULL,
  password    VARCHAR(80) NOT NULL
);
