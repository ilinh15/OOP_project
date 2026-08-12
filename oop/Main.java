public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        
        Doctor dr1 = new Doctor("Dr.Looi", 45, "17-538-6772", "Cardiology");
        Doctor dr2 = new Doctor("Dr.Chen", 38, "22-112-3345", "Neurology");
        Doctor dr3 = new Doctor("Dr.Wong", 50, "33-445-6677", "Orthopedics");
        Doctor dr4 = new Doctor("Dr.Tan", 42, "44-556-7788", "Pediatrics");
        Doctor dr5 = new Doctor("Dr.Lim", 35, "55-667-8899", "Dermatology");
        
        manager.addDoctor(dr1);
        manager.addDoctor(dr2);
        manager.addDoctor(dr3);
        manager.addDoctor(dr4);
        manager.addDoctor(dr5);
        
        // Example patient data
        Patient pt1 = new Patient("Rose", 30, "12-345-1567", "Inpatient", "Flu");
        Patient pt2 = new Patient("Jack", 26, "34-559-9991", "Outpatient", "Cold");
        Patient pt3 = new Patient("Lily", 40, "78-234-5566", "Inpatient", "Diabetes");
        Patient pt4 = new Patient("Tom", 55, "90-112-3344", "Outpatient", "Hypertension");
        Patient pt5 = new Patient("Anna", 33, "56-778-9900", "Inpatient", "Fracture");
        
        manager.addPatient(pt1);
        manager.addPatient(pt2);
        manager.addPatient(pt3);
        manager.addPatient(pt4);
        manager.addPatient(pt5);

        // Example staff data
        Staff sf1 = new Staff("Bob", 28, "555-1234", "Nurse", "Emergency");
        Staff sf2 = new Staff("Alice", 35, "555-5678", "Receptionist", "Front Desk");
        Staff sf3 = new Staff("John", 40, "555-8765", "Technician", "Radiology");
        
        
        manager.addStaff(sf1);
        manager.addStaff(sf2);
        manager.addStaff(sf3);

        System.out.println("\n\n===Patinet List===");
        manager.displayPatient();
        System.out.println("-".repeat(50));
        System.out.println("\n\n===Doctor List===");
        manager.displayDoctor();
        System.out.println("-".repeat(50));
        System.out.println("\n\n===Staff List===");
        manager.displayStaff();
        System.out.println("-".repeat(50));
        
        manager.addInpatientRecord(pt1, dr1, "Diabetic ketoacidosis", "IV fluids", "A3-113", "2025-11-23");
        manager.addOutpatientRecord(pt2, dr1, "Bacterial skin infection", "Antibiotic cream", "2025-11-22");
        manager.addInpatientRecord(pt3, dr2, "Severe migraine", "Painkillers", "B1-201", "2025-11-20");
        manager.addOutpatientRecord(pt4, dr4, "High blood pressure", "Medication", "2025-11-21");
        
        System.out.println("\n\n===Medical Record Histroy===");
        manager.displaymdRecord();
        System.out.println("-".repeat(50));
        
        Bill bill1 = new Bill(pt1, 2000);
        Bill bill2 = new Bill(pt2, 500);
        Bill bill3 = new Bill(pt3, 3000);
        manager.addBill(bill1);
        manager.addBill(bill2);
        manager.addBill(bill3);
        
        
        System.out.println("\n\n===Bill List===");
        manager.displayBill();
        System.out.println("-".repeat(50));
               
        manager.findPatient("Jack");
        manager.findDoctor("Dr.Looi");
    
        System.out.println("\nTest Appointment");
        //test 1
        manager.scheduleAppointment(pt2, dr1, "2025-11-26", "12:00", "Body Checkup");
        manager.scheduleAppointment(pt3, dr1, "2025-11-26", "12:00", "Body Checkup");
        //test 2
        manager.scheduleAppointment(pt4, dr4, "2025-11-28", "09:00", "Blood Pressure Check");
        //test 3
        manager.scheduleAppointment(pt5, dr3, "2025-11-29", "15:00", "Fracture Follow-up");        
        
        //display
        System.out.println("\n\n===Appointment List===");
        manager.displayApp();
        
        
        //cancel test 1
        System.out.println("\n\n===Cancel Appointment===");
        manager.cancelAppointment(pt4, dr4, "2025-11-28", "09:00");
        
        //display
        System.out.println("\n\n===Bill List===");
        manager.displayApp();
        
        //total
        manager.displayInformation();


    }
    
}
