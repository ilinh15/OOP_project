public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;
    private String reason;

    //constructors
    public Appointment(Patient patient, Doctor doctor, String date, String time, String reason) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.reason = reason;
    }

    // Getters
    public Patient getPatient() {
        return patient; }

    public Doctor getDoctor() {
        return doctor; }

    public String getDate() {
        return date; }

    public String getTime() {
        return time; }

    public String getReason() {
        return reason; }

    @Override
    public String toString() {
        return String.format("\nDate: %s \nTime: %s \nDoctor: %s \nPatient: %s \nReason: %s",
                date, time, doctor.getName(), patient.getName(), reason);
    }
}