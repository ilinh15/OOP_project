// HospitalDashboard.java
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalDashboard extends JFrame {


    private final List<Patient> patients = new ArrayList<>();
    private final List<Doctor> doctors = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();

    // Shared table models
    private final DefaultTableModel patientTableModel =
            new DefaultTableModel(new Object[]{"Name", "Age", "Diagnosis"}, 0);
    private final DefaultTableModel doctorTableModel =
            new DefaultTableModel(new Object[]{"Name", "Specialization"}, 0);
    private final DefaultTableModel appointmentTableModel =
            new DefaultTableModel(new Object[]{"Patient", "Doctor", "Date", "Status"}, 0);
    private final DefaultTableModel recordsTableModel =
            new DefaultTableModel(new Object[]{"Patient", "Diagnosis", "Doctor", "History"}, 0);

    // Patient form fields
    private JTextField patientNameField;
    private JTextField patientAgeField;
    private JTextField patientDiagField;
    private JComboBox<Patient> patientRecordCombo;
    private JComboBox<Patient> appointmentPatientCombo;
    private JComboBox<Doctor> appointmentDoctorCombo;

    public HospitalDashboard() {
        setTitle("Hospital Management System");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patient Registration", buildPatientPanel());
        tabs.addTab("Doctor Registration", buildDoctorPanel());
        tabs.addTab("Appointment Management", buildAppointmentPanel());
        tabs.addTab("Medical Records", buildRecordsPanel());

        add(tabs);
    }

    // --- Patient Tab ---
    private JPanel buildPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        patientNameField = new JTextField();
        patientAgeField = new JTextField();
        patientDiagField = new JTextField();

        form.add(new JLabel("Patient Name:"));
        form.add(patientNameField);
        form.add(new JLabel("Age:"));
        form.add(patientAgeField);
        form.add(new JLabel("Diagnosis:"));
        form.add(patientDiagField);

        JButton addButton = new JButton("Add / Update Patient");
        addButton.addActionListener(e -> addOrUpdatePatient());
        form.add(addButton);

        panel.add(form, BorderLayout.NORTH);

        JTable table = new JTable(patientTableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    private void addOrUpdatePatient() {
        try {
            String name = patientNameField.getText().trim();
            int age = Integer.parseInt(patientAgeField.getText().trim());
            String diagnosis = patientDiagField.getText().trim();

            if (name.isEmpty()) {
                showMessage("Name is required.");
                return;
            }

            Patient patient = findPatientByName(name);
            if (patient == null) {
                patient = createPatientInBackend(name, age, diagnosis); // TODO replace with your real call
                patients.add(patient);
                patientTableModel.addRow(new Object[]{patient.getName(), patient.getAge(), patient.getDiagnosis()});
            } else {
                updatePatientInBackend(patient, age, diagnosis); // TODO replace
                refreshPatientTable();
            }
            refreshPatientCombos();
            clearPatientForm();
        } catch (NumberFormatException ex) {
            showMessage("Age must be a number.");
        }
    }

    private void clearPatientForm() {
        patientNameField.setText("");
        patientAgeField.setText("");
        patientDiagField.setText("");
    }

    // --- Doctor Tab ---
    private JPanel buildDoctorPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));

        JTextField doctorNameField = new JTextField();
        JTextField doctorSpecField = new JTextField();

        form.add(new JLabel("Doctor Name:"));
        form.add(doctorNameField);
        form.add(new JLabel("Specialization:"));
        form.add(doctorSpecField);

        JButton addButton = new JButton("Register Doctor");
        addButton.addActionListener(e -> {
            String name = doctorNameField.getText().trim();
            String spec = doctorSpecField.getText().trim();
            if (name.isEmpty() || spec.isEmpty()) {
                showMessage("Both fields are required.");
                return;
            }
            Doctor doctor = createDoctorInBackend(name, spec); // TODO replace
            doctors.add(doctor);
            doctorTableModel.addRow(new Object[]{doctor.getName(), doctor.getSpecialization()});
            refreshDoctorCombo();
            doctorNameField.setText("");
            doctorSpecField.setText("");
        });
        form.add(addButton);

        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(new JTable(doctorTableModel)), BorderLayout.CENTER);

        return panel;
    }

    // --- Appointment Tab ---
    private JPanel buildAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel form = new JPanel(new GridLayout(6, 2, 5, 5));

        appointmentPatientCombo = new JComboBox<>();
        appointmentDoctorCombo = new JComboBox<>();
        JTextField dateField = new JTextField();
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Booked", "Cancelled", "Completed"});
        JTextArea notesArea = new JTextArea(3, 20);

        form.add(new JLabel("Patient:"));
        form.add(appointmentPatientCombo);
        form.add(new JLabel("Doctor:"));
        form.add(appointmentDoctorCombo);
        form.add(new JLabel("Date (YYYY-MM-DD HH:MM):"));
        form.add(dateField);
        form.add(new JLabel("Status:"));
        form.add(statusCombo);
        form.add(new JLabel("Diagnosis / Notes:"));
        form.add(new JScrollPane(notesArea));

        JButton bookButton = new JButton("Save Appointment");
        bookButton.addActionListener(e -> {
            Patient patient = (Patient) appointmentPatientCombo.getSelectedItem();
            Doctor doctor = (Doctor) appointmentDoctorCombo.getSelectedItem();
            String date = dateField.getText().trim();
            String status = (String) statusCombo.getSelectedItem();
            String notes = notesArea.getText().trim();

            if (patient == null || doctor == null || date.isEmpty()) {
                showMessage("Patient, doctor, and date are required.");
                return;
            }

            Appointment appointment = createAppointmentInBackend(patient, doctor, date, status, notes); // TODO replace
            appointments.add(appointment);
            refreshAppointmentTable();
            notesArea.setText("");
        });

        JButton cancelButton = new JButton("Cancel Selected");
        cancelButton.addActionListener(e -> {
            int row = getSelectedRowFromTable(appointmentTableModel);
            if (row < 0) return;
            Appointment appointment = appointments.get(row);
            cancelAppointmentInBackend(appointment); // TODO replace
            appointmentTableModel.setValueAt("Cancelled", row, 3);
        });

        form.add(bookButton);
        form.add(cancelButton);
        panel.add(form, BorderLayout.NORTH);

        JTable table = new JTable(appointmentTableModel);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    // --- Medical Records Tab ---
    private JPanel buildRecordsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        patientRecordCombo = new JComboBox<>();
        JButton loadButton = new JButton("Load Record");
        loadButton.addActionListener(e -> loadMedicalRecord());

        top.add(new JLabel("Select Patient:"));
        top.add(patientRecordCombo);
        top.add(loadButton);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(new JTable(recordsTableModel)), BorderLayout.CENTER);

        return panel;
    }

    private void loadMedicalRecord() {
        Patient patient = (Patient) patientRecordCombo.getSelectedItem();
        if (patient == null) {
            showMessage("Select a patient first.");
            return;
        }
        MedicalRecord record = fetchMedicalRecordFromBackend(patient); // TODO replace
        recordsTableModel.setRowCount(0);
        recordsTableModel.addRow(new Object[]{
                record.getPatientName(),
                record.getDiagnosis(),
                record.getDoctorName(),
                record.getHistorySummary()
        });
    }

    // --- Helper UI methods ---
    private void refreshPatientTable() {
        patientTableModel.setRowCount(0);
        for (Patient p : patients) {
            patientTableModel.addRow(new Object[]{p.getName(), p.getAge(), p.getDiagnosis()});
        }
    }

    private void refreshAppointmentTable() {
        appointmentTableModel.setRowCount(0);
        for (Appointment a : appointments) {
            appointmentTableModel.addRow(new Object[]{
                    a.getPatient().getName(),
                    a.getDoctor().getName(),
                    a.getDateTime(),
                    a.getStatus()
            });
        }
    }

    private void refreshPatientCombos() {
        appointmentPatientCombo.removeAllItems();
        patientRecordCombo.removeAllItems();
        for (Patient p : patients) {
            appointmentPatientCombo.addItem(p);
            patientRecordCombo.addItem(p);
        }
    }

    private void refreshDoctorCombo() {
        appointmentDoctorCombo.removeAllItems();
        for (Doctor d : doctors) {
            appointmentDoctorCombo.addItem(d);
        }
    }

    private int getSelectedRowFromTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        int row = table.getSelectedRow();
        if (row < 0) {
            showMessage("Select a table row first.");
        }
        return row;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }



    private Patient findPatientByName(String name) {
        return patients.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    private Patient createPatientInBackend(String name, int age, String diagnosis) {

        Patient p = new Patient(name, age, diagnosis);
        return p;
    }

    private void updatePatientInBackend(Patient patient, int age, String diagnosis) {
        patient.setAge(age);
        patient.setDiagnosis(diagnosis);
    }

    private Doctor createDoctorInBackend(String name, String specialization) {
        return new Doctor(name, specialization);
    }

    private Appointment createAppointmentInBackend(Patient patient, Doctor doctor, String date,
                                                   String status, String notes) {
        Appointment a = new Appointment(patient, doctor, date);
        a.setStatus(status);
        a.setNotes(notes);
        return a;
    }

    private void cancelAppointmentInBackend(Appointment appointment) {
        appointment.setStatus("Cancelled");
    }

    private MedicalRecord fetchMedicalRecordFromBackend(Patient patient) {

        Doctor doctor = doctors.isEmpty() ? null : doctors.get(0);
        return new MedicalRecord(patient.getName(),
                patient.getDiagnosis(),
                doctor == null ? "N/A" : doctor.getName(),
                "No previous appointments yet.");
    }


    static class Patient {
        private String name;
        private int age;
        private String diagnosis;
        public Patient(String name, int age, String diagnosis) {
            this.name = name; this.age = age; this.diagnosis = diagnosis;
        }
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getDiagnosis() { return diagnosis; }
        public void setAge(int age) { this.age = age; }
        public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
        @Override public String toString() { return name; }
    }

    static class Doctor {
        private final String name;
        private final String specialization;
        public Doctor(String name, String specialization) {
            this.name = name; this.specialization = specialization;
        }
        public String getName() { return name; }
        public String getSpecialization() { return specialization; }
        @Override public String toString() { return name + " (" + specialization + ")"; }
    }

    static class Appointment {
        private final Patient patient;
        private final Doctor doctor;
        private final String dateTime;
        private String status = "Booked";
        private String notes = "";
        public Appointment(Patient patient, Doctor doctor, String dateTime) {
            this.patient = patient; this.doctor = doctor; this.dateTime = dateTime;
        }
        public Patient getPatient() { return patient; }
        public Doctor getDoctor() { return doctor; }
        public String getDateTime() { return dateTime; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public void setNotes(String notes) { this.notes = notes; }
    }

    static class MedicalRecord {
        private final String patientName;
        private final String diagnosis;
        private final String doctorName;
        private final String historySummary;
        public MedicalRecord(String patientName, String diagnosis, String doctorName, String historySummary) {
            this.patientName = patientName;
            this.diagnosis = diagnosis;
            this.doctorName = doctorName;
            this.historySummary = historySummary;
        }
        public String getPatientName() { return patientName; }
        public String getDiagnosis() { return diagnosis; }
        public String getDoctorName() { return doctorName; }
        public String getHistorySummary() { return historySummary; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalDashboard().setVisible(true));
    }
}