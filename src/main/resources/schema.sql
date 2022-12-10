DROP DATABASE IF EXISTS javademo;

CREATE DATABASE javademo;

USE javademo;

CREATE TABLE vendors (
    ID INT NOT NULL AUTO_INCREMENT,
    vendorname VARCHAR(30),
    vendorphone VARCHAR(30),
    vendoraddress VARCHAR(50),
    PRIMARY KEY (ID)
);