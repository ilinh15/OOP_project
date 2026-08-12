import java.util.ArrayList;

public class Doctor extends Person implements Schedulable {
    private String specialization;

    ArrayList<Appointment> appointments = new ArrayList<>();

    //constructor
    public Doctor(String name, int age, String phoneNumber, String specialization) {
        super(name, age, phoneNumber);
        this.specialization = specialization;
    }

    //getters
    public String getSpecialization() {
        return specialization; }
    public ArrayList<Appointment> getAppointments() {
        return appointments; }
    
    public boolean checkTime(String date,String time){
        for(Appointment app : appointments){
            if(app.getDate().equals(date) && app.getTime().equals(time)){
                return false;
            }
        }
        return true;
            
    }
    // Interface implementation
    @Override
    public void scheduleAppointment(Appointment appointment) {
        appointments.add(appointment);
        System.out.println("Appointment scheduled for Dr. " + getName());
    }

    @Override
    public boolean cancelAppointment(Appointment appointment) {
        boolean removed = appointments.remove(appointment);
        System.out.println(removed ? "Appointment cancelled for Dr. " + getName() : "Appointment not found");
        return removed;
    }

    @Override
    public void viewSchedule() {
        System.out.println("\n=== Dr. " + getName() + "'s Schedule ===");
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
        return super.toString() + "\nSpecialization: " + specialization;
    }
}