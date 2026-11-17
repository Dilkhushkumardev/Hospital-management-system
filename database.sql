-- =================================================================
-- Hospital Management System (HMS) Database Schema & Initial Seed
-- =================================================================

CREATE DATABASE IF NOT EXISTS hospital_management_system;
USE hospital_management_system;

-- 1. Login Authentication
CREATE TABLE IF NOT EXISTS login (
    ID VARCHAR(50) NOT NULL PRIMARY KEY,
    PW VARCHAR(50) NOT NULL
);

-- 2. Patient Information
CREATE TABLE IF NOT EXISTS patient_info (
    ID_Type VARCHAR(50),
    number VARCHAR(50) PRIMARY KEY,
    Name VARCHAR(100),
    Gender VARCHAR(20),
    Patient_Disease VARCHAR(100),
    Room_Number VARCHAR(20),
    Time VARCHAR(100),
    Dposite VARCHAR(50)
);

-- 3. Room Management
CREATE TABLE IF NOT EXISTS room (
    room_no VARCHAR(20) PRIMARY KEY,
    Availability VARCHAR(50),
    Price VARCHAR(50),
    Room_Type VARCHAR(50)
);

-- 4. Employee Information
CREATE TABLE IF NOT EXISTS EMP_INFO (
    Name VARCHAR(100),
    Age VARCHAR(20),
    Salary VARCHAR(50),
    Phone_Number VARCHAR(50),
    Gmail VARCHAR(100),
    Aadhar_Number VARCHAR(50) PRIMARY KEY
);

-- 5. Department Directory
CREATE TABLE IF NOT EXISTS department (
    Department_Name VARCHAR(100) PRIMARY KEY,
    Phone_Number VARCHAR(50)
);

-- 6. Ambulance Fleet
CREATE TABLE IF NOT EXISTS Ambulance (
    Name VARCHAR(100),
    Gender VARCHAR(20),
    Car_Name VARCHAR(100),
    Available VARCHAR(50),
    Location VARCHAR(100)
);

-- =================================================================
-- INITIAL SEED DATA
-- =================================================================

INSERT INTO login (ID, PW) VALUES 
('admin', 'admin123'),
('root', '12345')
ON DUPLICATE KEY UPDATE PW=VALUES(PW);

INSERT IGNORE INTO room (room_no, Availability, Price, Room_Type) VALUES
('101', 'Available', '1500', 'Single Bed'),
('102', 'Available', '2500', 'Double Bed - AC'),
('103', 'Available', '3500', 'Deluxe Private Suite'),
('104', 'Available', '1000', 'General Ward'),
('105', 'Available', '5000', 'ICU Specialized');

INSERT IGNORE INTO department (Department_Name, Phone_Number) VALUES
('Emergency / Trauma', '+91 9876543210'),
('Cardiology', '+91 9876543211'),
('Neurology', '+91 9876543212'),
('Orthopedics', '+91 9876543213'),
('Pediatrics', '+91 9876543214'),
('General Surgery', '+91 9876543215');

INSERT IGNORE INTO EMP_INFO (Name, Age, Salary, Phone_Number, Gmail, Aadhar_Number) VALUES
('Dr. Rajesh Sharma', '45', '120000', '9811122233', 'rajesh.sharma@hms.com', '123456789012'),
('Dr. Priya Verma', '38', '110000', '9822233344', 'priya.verma@hms.com', '234567890123'),
('Nurse Anjali Gupta', '29', '45000', '9833344455', 'anjali.g@hms.com', '345678901234'),
('Admin Vikram Singh', '35', '50000', '9844455566', 'vikram.s@hms.com', '456789012345');

INSERT INTO Ambulance (Name, Gender, Car_Name, Available, Location) VALUES
('Ramesh Kumar', 'Male', 'Force Traveller (ICU)', 'Available', 'Main Hospital Gate'),
('Suresh Yadav', 'Male', 'Mahindra Bolero Neo', 'Available', 'City Trauma Center'),
('Amit Patel', 'Male', 'Tata Winger Ambulance', 'Busy', 'Sub-station North');
