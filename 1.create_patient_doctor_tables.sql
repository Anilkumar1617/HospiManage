CREATE TABLE Patient (
    patientId INTEGER PRIMARY KEY,
    name VARCHAR(255),
    dateOfBirth DATE,
    address VARCHAR(255),
    phoneNumber VARCHAR(20)
);

CREATE TABLE Doctor (
    doctorId INTEGER PRIMARY KEY,
    name VARCHAR(255),
    speciality VARCHAR(255)
);
