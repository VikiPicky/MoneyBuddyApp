--OPen Xampp MySQL and Create user full credencials 
--USERNAME = "dbuser";
--PASSWORD = "dbpassword";

--CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'dbpassword';
--GRANT ALL PRIVILEGES ON moneybuddy.* TO 'dbuser'@'localhost';

CREATE SCHEMA IF NOT EXISTS moneybuddy;
USE moneybuddy ;

CREATE TABLE IF NOT EXISTS moneybuddy.user (
  `UserID` INT NOT NULL auto_increment,
  `FirstName` VARCHAR(45) NOT NULL,
  `LastName` VARCHAR(45) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `Telephone` VARCHAR(45) NOT NULL,
  `Hash` VARCHAR(45) NOT NULL,
  `Active` INT(1) NOT NULL,
  PRIMARY KEY (`UserID`));
  
  CREATE TABLE IF NOT EXISTS `moneybuddy`.`category` (
  `CategoryID` INT NOT NULL auto_increment,
  `CategoryName` VARCHAR(45) NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`CategoryID`),
  CONSTRAINT FK_USER FOREIGN KEY (UserID)
    REFERENCES USER(UserID));

CREATE TABLE IF NOT EXISTS `moneybuddy`.`income` (
  `IncomeID` INT NOT NULL auto_increment,
  `Date` DATE NOT NULL,
  `Amount` DECIMAL(10,2) NOT NULL,
  `UserID` INT NOT NULL,
  PRIMARY KEY (`IncomeID`),
  CONSTRAINT FK_INCOME_USER FOREIGN KEY (UserID)
    REFERENCES USER(UserID));
  
  CREATE TABLE IF NOT EXISTS `moneybuddy`.`record` (
  `RecordID` INT NOT NULL auto_increment,
  `Date` DATE NOT NULL,
  `Amount` DECIMAL(10,2) NOT NULL,
  `Comment` LONGTEXT NULL DEFAULT NULL,
  `TaxAmount` DECIMAL(2,2) NULL DEFAULT NULL,
  `CategoryID` INT NOT NULL,
  PRIMARY KEY (`RecordID`),
  CONSTRAINT FK_CATEGORY FOREIGN KEY (CategoryID)
    REFERENCES CATEGORY(CategoryID));
  
  
-- DROP SCHEMA moneybuddy;

-- DELETE TABLE DATA

-- DELETE FROM CATEGORY;
-- DELETE FROM INCOME;
-- DELETE FROM RECORD;
-- DELETE FROM USER;
