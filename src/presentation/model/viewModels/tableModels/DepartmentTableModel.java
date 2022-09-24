package presentation.model.viewModels.tableModels;


import java.util.List;

public class DepartmentTableModel extends TableModelTemplate {


    public DepartmentTableModel(List<Object> list) {
        super(list, new String[]{"ID", "Direccion", "Porcentaje de Zona", "Coordenadas"},
                new Class[]{String.class, String.class, Double.class, String.class});
    }

    //FALTA CAMBIAR ESTE METODO PARA ADECUARLO A ESTA CLASE
    @Override
    protected void setRows(List<Object> list) {
            rows = new Object[list.size()][columns.length];
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < 4; j++){
                    switch (j){
                        case 0:
                            rows[i][j] = list.get(i);
                            break;
                        case 1:
                            rows[i][j] = list.get(i);
                            break;
                        case 2:
                            rows[i][j] = list.get(i);
                            break;
                        case 3:
                            rows[i][j] = list.get(i);
                            break;
                        case 4:
                            rows[i][j] = list.get(i);
                            break;
                        default:
                            break;
                    }
                }
            }
    }
}
