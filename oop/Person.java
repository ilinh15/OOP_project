public class Person {
    private String name;
    private int age;
    private String phoneNumber;

    //constructor
    public Person(String n,int a, String ph){
        name = n;
        age = a;
        phoneNumber =ph;
    }

    public void setName(String n){
        name = n;
    }

    public void setAge(int a){
        age = a;
    }

    public void setPhone(String ph){
        phoneNumber = ph;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getPhone(){
        return phoneNumber;
    }

    public String toString(){

        String s = String.format("\nName:" + name +"\nAge : " + age + "\nPhone Number : "+phoneNumber);
        return s;

    }
}
