SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
    id int PRIMARY KEY auto_increment,
    name VARCHAR,
    address VARCHAR,
    phone_number INTEGER
);

CREATE TABLE IF NOT EXISTS deliveries (
    id int PRIMARY KEY auto_increment,
    item VARCHAR,
    quantity VARCHAR,
    price INTEGER,
    destination VARCHAR,
    dispatch_time TIMESTAMP,
    delivery_time TIMESTAMP
);

--CREATE DATABASE sendit;
--\c sendit;
--
--CREATE TABLE IF NOT EXISTS users (
--    id SERIAL PRIMARY KEY,
--    name VARCHAR,
--    address VARCHAR,
--    phone_number INTEGER
--);
--
--CREATE TABLE IF NOT EXISTS deliveries (
--    id SERIAL PRIMARY KEY,
--    item VARCHAR,
--    quantity VARCHAR,
--    price INTEGER,
--    destination VARCHAR
--);
--
--CREATE DATABASE sendit_test WITH TEMPLATE sendit;
