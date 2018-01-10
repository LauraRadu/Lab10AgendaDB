public class Person {
    private double id;
    private String name;
    private String phoneNumber;

    Person(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    Person() {

    }

    public double getId() {
        return id;
    }

//nu ar trebui sa avem setter la id
//    public void setId(double id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
