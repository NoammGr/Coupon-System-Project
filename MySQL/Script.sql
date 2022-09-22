create database CouponSystem; -- creating data base

use CouponSystem;

select * from Companies;

select * from Customers;

select * from Coupons;

select locate(email, password) from Companies ;

drop table Companies;

drop table Categories;

drop table Coupons;

drop table customers_vs_coupons;

drop schema CouponSystem;

-- create tables with information
create table Companies(
id int primary key auto_increment,
name varchar(25),
email varchar(100),
password varchar(20)
);

create table Customers(
id int primary key auto_increment,
first_name varchar(25),
last_name varchar(25),
email varchar(100),
password varchar(20)
);

-- create table Categories(
-- id int primary key auto_increment,
-- name varchar(25)
-- );

create table Coupons(
id int primary key auto_increment,
company_id int,
foreign key (company_id) references Companies(id),
category enum('Food', 'Electricity', 'Restaurant', 'Vacation', 'Movies', 'Shopping'),
title varchar(50),
description varchar(150),
start_date date,
end_date date,
amount integer,
price double,
image varchar(1000)
);

create table customers_vs_coupons(
customer_id int ,
foreign key (customer_id) references Customers(id),
coupon_id int ,
foreign key (coupon_id) references Coupons(id),
primary key (customer_id, coupon_id)
);

