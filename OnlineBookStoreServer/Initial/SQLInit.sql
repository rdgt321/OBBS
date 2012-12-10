create database OBSS DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci;

use OBBS

create table book
(
name varchar(25) not null,
isbn varchar(25) not null primary key,
author varchar(20),
press varchar(20),
description varchar(255),
directoryID int,
publishdate Date,
price double,
specialprice double
);

create table cart_item
(
memberid int not null primary key,
bookisbn varchar(25) not null,
nowpirce double,
count int
);

create table collect
(
memberid int not null primary key,
bookisbn varchar(25) not null
);

create table coupons
(
couponsid int auto_increment not null primary key,
ownerid int not null,
discountrate double,
edndate date,
used bool
);

create table directory
(
directoryid int auto_increment  not null primary key,
name varchar(10)
);

create table equivalentbond
(
equivalentbondid int auto_increment not null primary key,
ownerid int not null,
equivalentdenomination double,
enddate date,
used bool
);

create table member
(
memberid int auto_increment not null primary key,
name varchar(12) not null,
password varchar(32),
phone varchar(16),
birth date
);

create table orders
(
orderid int auto_increment not null primary key,
memberid int,
totalprice double,
date date,
state int
);

create table order_item
(
orderid int not null primary key,
memberid int,
bookisbn varchar(25),
nowprice double,
count int
);

create table promotion
(
promotionid int auto_increment not null primary key,
leastintegral int,
startdate date,
enddate date,
discountrate double,
equivalentdenomination double,
bonduselimit double
);

create table user
(
userid int auto_increment not null primary key,
name varchar(12),
password varchar(32),
type int
);
