package presentation.model.viewModels.componentModels;


import presentation.model.viewModels.BranchTableInfo;

import java.util.List;

public class BranchTableModel extends TableModelTemplate<BranchTableInfo> {


    public BranchTableModel(List<BranchTableInfo> list) {
        super(list, new String[]{"ID", "Referencia", "Direccion", "Porcentaje de Zona", "Coordenadas"},
                new Class[]{String.class, String.class, String.class, Double.class, String.class});
    }

    //FALTA CAMBIAR ESTE METODO PARA ADECUARLO A ESTA CLASE
    @Override
    protected void setRows(List<BranchTableInfo> list) {
            rows = new Object[list.size()][columns.length];
            for(int i = 0; i < list.size(); i++){
                for(int j = 0; j < columns.length; j++){
                    switch (j){
                        case 0:
                            rows[i][j] = list.get(i).getId();
                            break;
                        case 1:
                            rows[i][j] = list.get(i).getReference();
                            break;
                        case 2:
                            rows[i][j] = list.get(i).getAddress();
                            break;
                        case 3:
                            rows[i][j] = list.get(i).getZoning_percentage();
                            break;
                        case 4:
                            rows[i][j] = list.get(i).getCoords();
                            break;
                        default:
                            break;
                    }
                }
            }
    }
}
