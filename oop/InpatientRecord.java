public class InpatientRecord extends MedicalRecord{
    private String roomNumber;
    private String date;
    
    public InpatientRecord(Patient patient,Doctor doctor,String diagnosis,String treatmentPlan, String roomNumber,String date){
        super(patient,doctor,diagnosis,treatmentPlan);
        this.roomNumber = roomNumber;
        this.date = date;
    }
    
    @Override
    public String getHealthRecords(){
        String s = "Inpatient Record";
        return s;
    }
    
    @Override
    public String toString(){
        String s = super.toString() + "\nRoom: "+ roomNumber + "\nDate : " + date;
        return s;
    }
           
}
