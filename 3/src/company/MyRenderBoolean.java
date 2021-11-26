package company;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyRenderBoolean implements TableCellRenderer {
    public MyRenderBoolean() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JCheckBox hboxBool = new JCheckBox("Совпала?");
        return hboxBool;
    }
}