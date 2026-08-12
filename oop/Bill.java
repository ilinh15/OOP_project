public class Bill{

    private double price;
    private Patient pt;

    public Bill(Patient pt,double price){
        this.pt = pt;
        this.price = price;
    }

    @Override
    public String toString() {
        String s = "\n======Bill======"+"\nPatient Name :"+ pt.getName() + "\nTotal Bill:Rm " + price;
        return s;
    }

    
}
