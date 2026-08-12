
import java.util.ArrayList;

public class Manager {
    ArrayList<Patient> ptList = new ArrayList<>();
    ArrayList<Doctor> drList = new ArrayList<>();
    ArrayList<Staff> sfList = new ArrayList<>();
    ArrayList<MedicalRecord> mdRecord = new ArrayList<>();
    ArrayList<Bill> billList = new ArrayList<>();
    ArrayList<Appointment> appList = new ArrayList<>();
    
    
    //add Patient information
    public void addPatient(Patient patient){
        ptList.add(patient);
    }
    
    
    //Displau all Patient information
    public void displayPatient(){
    for(Patient pt : ptList)
        System.out.println(pt);
        System.out.print("-".repeat(30));
    }
    
    public void findPatient(String name){
        boolean found = false;
        
        for(Patient pt : ptList){
            if(pt.getName().equalsIgnoreCase(name)){
                System.out.print("\nFound !!!");
                System.out.println(pt);
                found = true;
                break;
            }
        }
        
        if(!found){
            System.out.println("Not Found........");
        }
    }
    
    
    //add Doctor information
    public void addDoctor(Doctor doctor){
        drList.add(doctor);
    }
    
    public void displayDoctor(){
        for(Doctor dr : drList)
            System.out.println(dr);
    }
    
        public void findDoctor(String name){
        boolean found = false;
        
        for(Doctor dr : drList){
            if(dr.getName().equalsIgnoreCase(name)){
                System.out.print("\nFound !!!");
                System.out.println(dr);
                found = true;
                break;
            }
        }
        
        if(!found){
            System.out.println("Not Found........");
        }
    }
    
    public void addStaff(Staff staff){
        sfList.add(staff);
    }
    
    public void displayStaff(){
        for(Staff sf : sfList){
            System.out.println(sf);
            System.out.print("-".repeat(30));
        }
    }
    
    public void addInpatientRecord(Patient patient,Doctor doctor,String diagnosis,String treatmentPlan, String roomNumber,String date){
        InpatientRecord inRecord = new InpatientRecord(patient,doctor,diagnosis,treatmentPlan,roomNumber,date);
        mdRecord.add(inRecord);
    }
    
        public void addOutpatientRecord(Patient patient,Doctor doctor,String diagnosis,String treatmentPlan,String date){
        OutpatientRecord outRecord = new OutpatientRecord(patient,doctor,diagnosis,treatmentPlan,date);
        mdRecord.add(outRecord);
    }
        
      public void displaymdRecord(){
        for(MedicalRecord md : mdRecord)
            System.out.println(md);
    }
      
    public void scheduleAppointment(Patient patient, Doctor doctor, String date, String time, String reason){
        Appointment newApp = new Appointment(patient,doctor,date,time,reason);
        
        System.out.println("\n=====Try to Make Appointment=====");
        System.out.println("Doctor : " + doctor.getName());
        System.out.println("Patient : " + patient.getName());
        System.out.println("Time : "+ date + " " + time);
        
        if(!doctor.checkTime(date, time)){
            System.out.println("Failed,Doctor already has other Appointment at same time");
        }
        else{
        //add appoointment to patient and doctor
        patient.scheduleAppointment(newApp);
        doctor.scheduleAppointment(newApp);
        
        appList.add(newApp);
        System.out.println("Appointment Successful");
        }
        
    }
    
    public void cancelAppointment(Patient patient,Doctor doctor,String date,String time){
        Appointment cancel = null;
        for(Appointment canApp : appList){
            if(canApp.getPatient().equals(patient) && canApp.getDoctor().equals(doctor)
                    && canApp.getDate().equals(date) && canApp.getTime().equals(time))
            {
                cancel = canApp;
                break;
            }
        }
        
        if(cancel != null){
            patient.cancelAppointment(cancel);
            doctor.cancelAppointment(cancel);
            
            appList.remove(cancel);
            System.out.println("Appointment cancel");
        }
        else
            System.out.println("\nNot Found");
    }
    
    public void displayApp(){
        for(Appointment app : appList){
        System.out.println(app);
        }
    }
        
    public void addBill(Bill bill){
        billList.add(bill);
    }
    
    public void displayBill(){
        for(Bill bill : billList)
            System.out.println(bill);
    }
    
    public void displayInformation(){
        System.out.println("\n=======Total=======");
        System.out.println("Total Patient : " + ptList.size());
        System.out.println("Total Doctor : " + drList.size());
        System.out.println("Total Staff : " + sfList.size());
        System.out.println("Total Medecal Record : " + mdRecord.size());
    }
    
}
