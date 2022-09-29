package business;

import data.Employee;
import presentation.model.viewModels.EmployeeInfo;

public class EmployeeParser {
    public static Employee toEmployee(EmployeeInfo e){
        //Create Employee
        String id = e.getId();
        String name = e.getName();
        String phone_number = e.getPhone_number();
        double base_salary = e.getBase_salary();

        //FALTA INCORPORAR A BRNACHDAO LA BUSQUEDA POR REFERENCIA

        return new Employee(id/*, name, phone_number, base_salary, branch*/);
    }
}
