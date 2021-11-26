package company;

import javax.swing.table.AbstractTableModel;
@SuppressWarnings("serial")
public class GornerTable extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTable(Double from, Double to, Double step, Double[] coeff) { this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }



    public Double getFrom() {
        return this.from;
    }

    public Double getTo() {
        return this.to;
    }

    public Double getStep() {
        return this.step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return (new Double(Math.ceil((this.to - this.from) / this.step))).intValue() + 1;
    }

    public Object getValueAt(int row, int col) {
        Boolean sim = false;
        Double x = this.from + this.step * (double)row;
        Double result = 0.0D;

        for(int i = 0; i < this.coefficients.length - 1; ++i) {
            result = (result + this.coefficients[i]) * x;
        }

        result = result + this.coefficients[this.coefficients.length - 1];
        if (col == 0) {
            return x;
        } else if (col == 1) {
            return result;
        } else {
            String s = result.toString();
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                sim = true;
            }

            return sim;
        }
    }

    public String getColumnName(int col) {
        switch(col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Точное значение";
        }
    }

    public Class<?> getColumnClass(int col) {
        return col == 2 ? Boolean.class : Double.class;
    }
}
