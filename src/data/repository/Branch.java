package data.repository;

import java.util.List;

public class Branch {
    private String id;
    private String reference;
    private String address;
    private Double zoning_percentage;
    private Coordinates coords;
    private List<Employee> employees;

    public Branch(){

    }
    public Branch(String id) {
        this.id = id;
    }

    public Branch(String id, String reference,String address, double zoning_percentage) {
        this.id = id;
        this.reference = reference;
        this.address = address;
        this.zoning_percentage = zoning_percentage;
        this.coords = coords;
        this.employees = employees;
    }

    public String getId() {
        return id;
    }
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getZoning_percentage() {
        return zoning_percentage;
    }

    public void setZoning_percentage(Double zoning_percentage) {
        this.zoning_percentage = zoning_percentage;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", zoning_percentage=" + zoning_percentage +
                ", coords=" + coords +
                ", employees=" + employees +
                '}';
    }
}
