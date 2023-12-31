CREATE DATABASE tsherpa;

USE tsherpa;

CREATE TABLE role(
role_id INT PRIMARY KEY AUTO_INCREMENT,
role VARCHAR(255) DEFAULT NULL
);

CREATE TABLE user(
user_id INT PRIMARY KEY AUTO_INCREMENT,
active INT DEFAULT 0,
login_id VARCHAR(255) NOT NULL,
user_name VARCHAR(255) NOT NULL,
password VARCHAR(300) NOT NULL,
regdate DATETIME DEFAULT CURRENT_TIMESTAMP()
);

CREATE TABLE user_role(
user_id INT NOT NULL,
role_id INT NOT NULL,
PRIMARY KEY (user_id, role_id)
);

CREATE table board(
bno INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(200) NOT NULL,
content VARCHAR(2000) NOT NULL,
resdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
visited int DEFAULT 0,
id VARCHAR(50),
commentCnt int DEFAULT 0
);

CREATE TABLE keyword (
kno BIGINT AUTO_INCREMENT PRIMARY KEY,
word VARCHAR(200) NOT NULL,
uid VARCHAR(255) NOT NULL
);

CREATE TABLE notification (
nno BIGINT AUTO_INCREMENT PRIMARY KEY,
word VARCHAR(200) NOT NULL,
bno INT NOT NULL,
uid VARCHAR(255) NOT NULL
);


