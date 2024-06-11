package subject;
import java.sql.*;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "Vijay";

    public static void main(String[] args) {
        try {
            // Load the Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish database connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create a Scanner object for user input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nHospital Management System");
                System.out.println("1. Add Patient");
                System.out.println("2. Add Doctor");
                System.out.println("3. View Patients");
                System.out.println("4. View Doctors");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addPatient(connection, scanner);
                        break;
                    case 2:
                        addDoctor(connection, scanner);
                        break;
                    case 3:
                        viewPatients(connection);
                        break;
                    case 4:
                        viewDoctors(connection);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        connection.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addPatient(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter date of birth (DD-MM-"
        		+ "YYYY): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        long phoneNumber = scanner.nextLong();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO patients (patientid, name, dateofbirth, address, phonenumber) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, patientId);
        statement.setString(2, name);
        statement.setString(3, dateOfBirth);
        statement.setString(4, address);
        statement.setLong(5, phoneNumber);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Patient added successfully!");
        } else {
            System.out.println("Failed to add patient.");
        }
    }

    private static void addDoctor(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter speciality: ");
        String speciality = scanner.nextLine();

        PreparedStatement statement = connection.prepareStatement("INSERT INTO doctors (doctorid, name, speciality) VALUES (?, ?, ?)");
        statement.setInt(1, doctorId);
        statement.setString(2, name);
        statement.setString(3, speciality);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Doctor added successfully!");
        } else {
            System.out.println("Failed to add doctor.");
        }
    }

    private static void viewPatients(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM patients");
        System.out.println("\nPatients:");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("patientid") + ", Name: " + resultSet.getString("name") +
                    ", Date of Birth: " + resultSet.getString("dateofbirth") + ", Address: " + resultSet.getString("address") +
                    ", Phone Number: " + resultSet.getLong("phonenumber"));
        }
    }

    private static void viewDoctors(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM doctors");
        System.out.println("\nDoctors:");
        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("doctorid") + ", Name: " + resultSet.getString("name") +
                    ", Speciality: " + resultSet.getString("speciality"));
        }
    }
}
