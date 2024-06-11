package subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    private Connection connection;

    public DoctorDAO(Connection connection) {
        this.connection = connection;
    }

    public Doctor getDoctorById(int doctorId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM doctors WHERE doctorid = ?");
        statement.setInt(1, doctorId);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            return extractDoctorFromResultSet(rs);
        } else {
            return null;
        }
    }

    public List<Doctor> getAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM doctors");

        while (rs.next()) {
            doctors.add(extractDoctorFromResultSet(rs));
        }
        return doctors;
    }

    public void insertDoctor(Doctor doctor) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO doctors (name, speciality) VALUES (?, ?)");
        statement.setString(1, doctor.getName());
        statement.setString(2, doctor.getSpeciality());
        statement.executeUpdate();
    }

    public void updateDoctor(Doctor doctor) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE doctors SET name=?, speciality=? WHERE doctorid=?");
        statement.setString(1, doctor.getName());
        statement.setString(2, doctor.getSpeciality());
        statement.setInt(3, doctor.getDoctorId());
        statement.executeUpdate();
    }

    public void deleteDoctor(int doctorId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM doctors WHERE doctorid=?");
        statement.setInt(1, doctorId);
        statement.executeUpdate();
    }

    private Doctor extractDoctorFromResultSet(ResultSet rs) throws SQLException {
        int doctorId = rs.getInt("doctorid");
        String name = rs.getString("name");
        String speciality = rs.getString("speciality");
        return new Doctor(doctorId, name, speciality);
    }
}
