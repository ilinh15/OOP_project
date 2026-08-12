public interface Schedulable {
    void scheduleAppointment(Appointment appointment);
    boolean cancelAppointment(Appointment appointment);
    void viewSchedule();
}