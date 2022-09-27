package presentation.model.viewModels.tableModels;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public abstract class TableModelTemplate<T> extends DefaultTableModel {
    protected String[] columns;
    protected Class[] columnClass;
    protected Object[][] rows;

    public TableModelTemplate(List<T> list, String[] columns, Class[] columnClass){
        this.columns = columns;
        this.columnClass = columnClass;
        setRows(list);
        setDataVector(rows, columns);
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
    protected abstract void setRows(List<T> list);
}
