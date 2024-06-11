CREATE TABLE Patient (
    PatientId INT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    address VARCHAR(255),
    phoneNumber VARCHAR(20)
);

CREATE TABLE Doctor (
    doctorId INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    speciality VARCHAR(255) NOT NULL
);
