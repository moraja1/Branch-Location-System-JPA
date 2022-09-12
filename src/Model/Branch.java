package Model;

import java.util.Set;

public class Branch {
    private String id;
    private String address;
    private Double zoningPercentage;
    private Coordinates coords;
    private Set<Employee> employees;

    public Branch(){

    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getZoningPercentage() {
        return zoningPercentage;
    }

    public void setZoningPercentage(Double zoningPercentage) {
        this.zoningPercentage = zoningPercentage;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
