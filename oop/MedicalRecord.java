public abstract class MedicalRecord{
    
    protected String diagnosis;
    protected String treatmentPlan;
    protected Patient patient;
    protected Doctor doctor;
    
    public MedicalRecord(Patient patient,Doctor doctor,String diagnosis,String treatmentPlan){
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
    }

    public abstract String getHealthRecords();
    
    @Override
    public String toString(){
        String s = String.format("\nName: "+ patient.getName()+"\nDoctor: "+doctor.getName()+"\nHealth Record: "+ getHealthRecords() 
                                            + "\nDiagnosis :" + diagnosis+"\nTreatment Plan:"+treatmentPlan);
        return s;
    }
    
}
