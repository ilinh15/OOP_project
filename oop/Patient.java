import java.util.ArrayList;

public class Patient extends Person implements Schedulable {
    private String medicalHistory;
    private String diagnosis;
    private Doctor assignedDoctor;

    ArrayList<Appointment> appointments = new ArrayList<>();

    //constructors
    public Patient(String name, int age, String phoneNumber, String medicalHistory, String diagnosis) {
        super(name, age, phoneNumber);
        this.medicalHistory = medicalHistory;
        this.diagnosis = diagnosis;
    }

    //getters and setters
    public String getMedicalHistory() {
        return medicalHistory; }

    public String getDiagnosis() {
        return diagnosis; }

    public Doctor getAssignedDoctor() {
        return assignedDoctor; }

    public ArrayList<Appointment> getAppointments() {
        return appointments; }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory; }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis; }

    public void assignDoctor(Doctor doctor) {
        this.assignedDoctor = doctor; }

    // Interface implementation
    @Override
    public void scheduleAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment scheduled for " + getName());
    }

    @Override
    public boolean cancelAppointment(Appointment appointment) {
        boolean removed = appointments.remove(appointment);
        System.out.println(removed ? "\nAppointment cancelled for " + getName() : "Appointment not found");
        return removed;
    }

    @Override
    public void viewSchedule() {
        System.out.println("\n=== " + getName() + "'s Appointment Schedule ===");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            for (Appointment app : appointments) {
                System.out.println(app);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nMedical History: " + medicalHistory +
                "\nDiagnosis: " + diagnosis +
                "\nDoctor: " + (assignedDoctor != null ? assignedDoctor.getName() : "None");
    }
}