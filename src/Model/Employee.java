package Model;

public class Employee extends Person{
    private Double base_salary;
    private Branch branch;
    public Employee(String id, String name, String phone_number, Double baseSalary, Branch branch) {
        super(id, name, phone_number);
        this.base_salary = baseSalary;
        this.branch = branch;
    }
    public Double getBase_salary() {
        return base_salary;
    }
    public void setBase_salary(Double base_salary) {
        this.base_salary = base_salary;
    }
    public Branch getBranch() {
        return branch;
    }
    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
