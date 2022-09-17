package Model;

public class Person {
    protected String id;
    protected String name;
    protected String phone_number;
    public Person(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phone_number = phoneNumber;
    }

    public Person(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
