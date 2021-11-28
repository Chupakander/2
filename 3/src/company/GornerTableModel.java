package company;

import javax.swing.table.AbstractTableModel;
//@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel
{
    private	Double[] coefficients;
    private	Double from;
    private	Double to;
    private	Double step;
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients)
    {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom()
    {
        return from;
    }
    public Double getTo()
    {
        return to;
    }
    public Double getStep()
    {
        return step;
    }
    public int getColumnCount()
    {
        // В данной модели два столбца
        return 3;
    }
    public int getRowCount()
    {
        // Вычислить количество точек между началом и концом отрезка исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }
    public Object getValueAt(int row, int col)
    {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;
        if (col==0)
        {
            // Если запрашивается значение 1-го столбца, то это X
            return x;
        }
        if(col==1)
        {
            // Если запрашивается значение 2-го столбца, то это значение многочлена
            Double result = 0.0;
            for ( int i = coefficients.length-1; i >= 0; i-- )
                result = coefficients[i]+result*x;
            return result;
        }
        else
        {
            Double result = 0.0;
            double req=0.0;
            int number;
            int EndResult = 0;
            for ( int i = coefficients.length-1; i >= 0; i-- )
            {
                result = coefficients[i]+result*x;
            }
            req=result%1;
            if (req> 0 && req<1)
            return EndResult == 1 ;
            else
                return EndResult == 0;
        }
    }
    private void setText(String string) {
        // TODO Auto-generated method stub

    }
    private int indexOf(char c) {
        // TODO Auto-generated method stub
        return 0;
    }
    public String getColumnName(int col)
    {
        switch (col)
        {
            case 0:
                // Название 1-го столбца
                return "Значение X"	;

            case 1:
                return "Значение многочлена";

            default:
                return "Точное значение";
        }
    }
    public Class<?> getColumnClass(int col)
    {
        switch (col)
        {
            case 0 :
                return Double.class;
            case 1 :
               return Double.class;
            default:
                return Boolean.class ;

        }
    }
}