package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Branch {
    private String id;
    private String address;
    private Double zoning_percentage;
    private Coordinates coords;
    private HashMap<String, Employee> employees;

    public Branch(){

    }

    public Branch(String id) {
        this.id = id;
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

    public HashMap<String, Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(HashMap<String, Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Branch{").append("id='").append(id).append('\'').append(", address='").append(address).append('\'')
                .append(", zoning_percentage=").append(zoning_percentage).append(", coords=").append(coords);
        List<Employee> e = new ArrayList<Employee>(employees.values());
        e.get(0).setBranch_callAs(true);
        string.append(", employees=").append(employees).append('}');
        e.get(0).setBranch_callAs(false);
        return string.toString();
    }
}
