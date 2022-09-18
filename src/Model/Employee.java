package Model;

public class Employee extends Person{
    private Double base_salary;
    private Branch branch;
    private static boolean branch_call = false;
    public Employee(String id, String name, String phone_number, Double baseSalary, Branch branch) {
        super(id, name, phone_number);
        this.base_salary = baseSalary;
        this.branch = branch;
    }

    public Employee(String id) {
        super(id);
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
    public void setBranch_callAs(boolean flag){
        branch_call = flag;
    }
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Employee{").append("base_salary=").append(base_salary);
        if(branch_call){
            string.append(", branch=").append(branch.getId());
        }else{
            string.append(", branch=").append(branch);
        }
        string.append(", id='").append(id).append('\'').append(", name='").append(name).append('\'').
                append(", phone_number='").append(phone_number).append('\'').append('}');

        return string.toString();
    }
}
