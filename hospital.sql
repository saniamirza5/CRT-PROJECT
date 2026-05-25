CREATE DATABASE hospitaldb;
USE hospitaldb;
CREATE TABLE patients (

    patient_id INT PRIMARY KEY,

    patient_name VARCHAR(100),

    bed_number INT,

    days INT
);
SHOW TABLES;
DESC patients;
SELECT * FROM patients;

