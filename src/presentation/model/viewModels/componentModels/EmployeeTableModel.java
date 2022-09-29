package presentation.model.viewModels.componentModels;

import presentation.model.viewModels.EmployeeInfo;

import java.util.List;

public class EmployeeTableModel extends TableModelTemplate<EmployeeInfo> {

    public EmployeeTableModel(List<EmployeeInfo> list){
        super(list, new String[]{"ID", "Nombre", "Numero de Telefono", "Salario Base", "Referencia de Sucursal", "Porcentaje por Zona", "Salario Total"},
                new Class[]{String.class, String.class, String.class, Double.class, String.class, Double.class, Double.class});
    }
    @Override
    protected void setRows(List<EmployeeInfo> list) {
        rows = new Object[list.size()][columns.length];
        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < columns.length; j++){
                switch (j){
                    case 0:
                        rows[i][j] = list.get(i).getId();
                        break;
                    case 1:
                        rows[i][j] = list.get(i).getName();
                        break;
                    case 2:
                        rows[i][j] = list.get(i).getPhone_number();
                        break;
                    case 3:
                        rows[i][j] = list.get(i).getBase_salary();
                        break;
                    case 4:
                        rows[i][j] = list.get(i).getBranch_reference();
                        break;
                    case 5:
                        rows[i][j] = list.get(i).getZoning_percentage();
                        break;
                    case 6:
                        rows[i][j] = list.get(i).getTotal_salary();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
