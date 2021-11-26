package company;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class GornerTableCellRenderer implements TableCellRenderer {
        private JPanel panel = new JPanel();
        private JLabel label = new JLabel();
        private DecimalFormat formater = (DecimalFormat)NumberFormat.getInstance();

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelect, boolean hasFocus, int row, int col) {
            String znakX = this.formater.format(value);
            this.label.setText(znakX);
            this.panel.setBackground(Color.WHITE);
            this.label.setForeground(Color.BLACK);
            if (col == 1 && Double.parseDouble(znakX) < 0.0D) {
                this.panel.setBackground(Color.GREEN);
                this.label.setForeground(Color.RED);
            }

            return this.panel;
        }

        public GornerTableCellRenderer() {
            this.panel.add(this.label);
            this.panel.setLayout(new FlowLayout(0));
            this.formater.setMaximumFractionDigits(5);
            this.formater.setGroupingUsed(false);
            DecimalFormatSymbols dotDouble = this.formater.getDecimalFormatSymbols();
            dotDouble.setDecimalSeparator('.');
            this.formater.setDecimalFormatSymbols(dotDouble);
        }
    }