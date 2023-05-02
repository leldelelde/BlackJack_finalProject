CREATE TABLE usersBJ (
userID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
username varchar(15) NOT NULL,
password varchar(45) NOT NULL,
fullName varchar(45) NOT NULL,
age int(3) NOT NULL,
email varchar(45) NOT NULL
);
SELECT * FROM usersBJ;

CREATE TABLE gameResults(
id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
user_id int REFERENCES usersBJ (userID),
wins int NOT NULL,
losses int NOT NULL
);
DROP TABLE IF EXISTS gameResults;
SELECT * FROM gameResults;