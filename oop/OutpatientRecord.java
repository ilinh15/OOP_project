
public class OutpatientRecord extends MedicalRecord{
    private String date;
    
    public OutpatientRecord(Patient patient,Doctor doctor,String diagnosis,String treatmentPlan,String date){
        super(patient,doctor,diagnosis,treatmentPlan);
        this.date = date;
    }
    
    @Override
    public String getHealthRecords(){
        String s = "Outpatien Record";
        return s;
    }
    
    @Override
    public String toString(){
        String s = super.toString() + "\nDate : " + date;
        return s;
    }
}
