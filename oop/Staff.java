public class Staff extends Person{
    private String role;
    private String department;

    public Staff(String n,int a,String ph, String r,String dp){
        super(n,a,ph);
        role = r;
        department = dp;
    }

    @Override
    public String toString(){
        String s = super.toString() + "\nRole : "+ role + "\nDepartment : " + department;
        return s;
    }
}
