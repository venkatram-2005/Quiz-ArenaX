create database quizarena ;

use quizarena ;

CREATE TABLE subjects (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    subjectname VARCHAR(50)
);

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(20),
    useremail VARCHAR(40),
    userpassword VARCHAR(20),
    usermobile VARCHAR(20)
);


CREATE TABLE admins (
    username VARCHAR(50),
    userpassword VARCHAR(50)
);

CREATE TABLE quizresults (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(255),
    subject_name VARCHAR(255),
    score DECIMAL(5,2),
    date_taken TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- In case if you want to push some question into subjects using the MySQL Workbech ot cmd use this table and replace suvject name

CREATE TABLE subject_name (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    question TEXT NOT NULL,
    option_a VARCHAR(255) NOT NULL,
    option_b VARCHAR(255) NOT NULL,
    option_c VARCHAR(255) NOT NULL,
    option_d VARCHAR(255) NOT NULL,
    answer_option ENUM('a', 'b', 'c', 'd') NOT NULL
);


