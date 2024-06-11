package subject;
public class Doctor {
    private int doctorId;
    private String name;
    private String speciality;
    
    // Constructor
    public Doctor(int doctorId, String name, String speciality) {
        this.doctorId = doctorId;
        this.name = name;
        this.speciality = speciality;
    }
    
    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
