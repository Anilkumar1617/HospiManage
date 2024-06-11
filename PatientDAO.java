package subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    public Patient getPatientById(int patientId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM patients WHERE patientid = ?");
        statement.setInt(1, patientId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return extractPatientFromResultSet(rs);
        } else {
            return null;
        }
    }

    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM patients");

        while (rs.next()) {
            patients.add(extractPatientFromResultSet(rs));
        }
        return patients;
    }

    public void insertPatient(Patient patient) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO patients (name, dateofbirth, address, phonenumber) VALUES (?, ?, ?, ?)");
        statement.setString(1, patient.getName());
        statement.setString(2, patient.getDateOfBirth());
        statement.setString(3, patient.getAddress());
        statement.setLong(4, patient.getPhoneNumber());
        statement.executeUpdate();
    }

    public void updatePatient(Patient patient) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE patients SET name=?, dateofbirth=?, address=?, phonenumber=? WHERE patientid=?");
        statement.setString(1, patient.getName());
        statement.setString(2, patient.getDateOfBirth());
        statement.setString(3, patient.getAddress());
        statement.setLong(4, patient.getPhoneNumber());
        statement.setInt(5, patient.getPatientId());
        statement.executeUpdate();
    }

    public void deletePatient(int patientId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM patients WHERE patientid=?");
        statement.setInt(1, patientId);
        statement.executeUpdate();
    }

    private Patient extractPatientFromResultSet(ResultSet rs) throws SQLException {
        int patientId = rs.getInt("patientid");
        String name = rs.getString("name");
        String dateOfBirth = rs.getString("dateofbirth");
        String address = rs.getString("address");
        long phoneNumber = rs.getLong("phonenumber");
        return new Patient(patientId, name, dateOfBirth, address, phoneNumber);
    }
}
