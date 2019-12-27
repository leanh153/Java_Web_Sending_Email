CREATE  database project5;
USE project5;

create table users (id bigint not null auto_increment, email varchar(255), password varchar(255), signupTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, primary key (id)) ;


insert into users(email, password) value("leanhtestsendemail@gmail.com", "Hoimanchi2#");
