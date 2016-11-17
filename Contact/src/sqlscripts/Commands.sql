/*
 * Copyright (c) 2016 Betsey McCarthy, Jen Rhodes, Dawn Winsor 
 */

/*Drop database*/
DROP DATABASE IF EXISTS students;

/*Create database*/
CREATE DATABASE students;
Use students;

/* Create Table*/
CREATE TABLE if not exists `students`.`Contacts` (
	 `ID` INT NOT NULL AUTO_INCREMENT,
	 `FirstName` VARCHAR(45) NOT NULL,
	 `MiddleName` VARCHAR(45) NULL,
	 `LastName` VARCHAR(45) NOT NULL,
	 `Email` VARCHAR(45) NOT NULL,
	 `Major` VARCHAR(45) NOT NULL,
PRIMARY KEY (`ID`));

	
/*Insert five name entries into the DB*/
	INSERT INTO Contacts
		(firstname, middlename, lastname, email, major)
	VALUES
		('Marshall', 'C', 'Brown', 'mbrown62@elon.edu', 'Computer Science'),
		('Haris', NULL, 'Cesko', 'hcesko@elon.edu', 'Computer Science'),
		('Maddie', 'C', 'Chili', 'mchili@elon.edu', 'Computer Science'),
		('Keith', 'A', 'Davis', 'kdavis36@elon.edu', 'Computer Science'),
		('Z', 'H', 'Deirmendjian', 'zdeirmendjian@elon.edu', 'Computer Science'),
		('Ben', 'M', 'Fobert', 'bfobert@elon.edu', 'Computer Science'),
		('Edward', 'E', 'Elon', 'eelon@elon.edu', 'Information Science');

SELECT * FROM Contacts