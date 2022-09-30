package presentation.model.viewModels;

public class EmployeeInfo {
    private String id;
    private String name;
    private String phone_number;
    private double base_salary;
    private String branch_reference;
    private double zoning_percentage;
    private double total_salary;

    public EmployeeInfo(String id, String name, String phone_number, double base_salary, String branch_reference, double zoning_percentage, double total_salary) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.base_salary = base_salary;
        this.branch_reference = branch_reference;
        this.zoning_percentage = zoning_percentage;
        this.total_salary = total_salary;
    }
    public EmployeeInfo(String id, String name, String phone_number, double salary, String reference) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.base_salary = salary;
        this.branch_reference = reference;
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

    public double getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(double base_salary) {
        this.base_salary = base_salary;
    }

    public String getBranch_reference() {
        return branch_reference;
    }

    public void setBranch_reference(String branch_reference) {
        this.branch_reference = branch_reference;
    }

    public double getZoning_percentage() {
        return zoning_percentage;
    }

    public void setZoning_percentage(double zoning_percentage) {
        this.zoning_percentage = zoning_percentage;
    }

    public double getTotal_salary() {
        return total_salary;
    }

    public void setTotal_salary(double total_salary) {
        this.total_salary = total_salary;
    }
}
