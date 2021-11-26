package company;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainFraim extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private Double[] coeff;
    private JFileChooser fileChooser = null;
    private JMenuItem saveToTextMenuItem;
    private JMenuItem saveToGraphicsMenuItem;
    private JMenuItem searchValueMenuItem;
    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;
    private Box hBoxResult;
    private GornerTableCellRenderer znak = new GornerTableCellRenderer();
    private MyRenderBoolean strokaBOOL = new MyRenderBoolean();
    private GornerTable data;

    public MainFraim(Double[] coeff) {
        super("Табулирование многочлена по схеме Горнера");
        this.coeff = coeff;
        this.setSize(700, 500);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 700) / 2, (kit.getScreenSize().height - 500) / 2);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);
        JMenu tableMenu = new JMenu("Таблица");
        menuBar.add(tableMenu);
        JMenu helpMenu = new JMenu("Справка");
        menuBar.add(helpMenu);
        Action saveToTextAction = new AbstractAction("Сохранить в текстовый файл") {
            public void actionPerformed(ActionEvent event) {
                if (MainFraim.this.fileChooser == null) {
                    MainFraim.this.fileChooser = new JFileChooser();
                    MainFraim.this.fileChooser.setCurrentDirectory(new File("."));
                }

                if (MainFraim.this.fileChooser.showSaveDialog(MainFraim.this) == 0) {
                    MainFraim.this.saveToTextFile(MainFraim.this.fileChooser.getSelectedFile());
                }

            }
        };
        this.saveToTextMenuItem = fileMenu.add(saveToTextAction);
        this.saveToTextMenuItem.setEnabled(false);
        Action saveToGraphicsAction = new AbstractAction("Сохранить данные\tдля  построения графика") {
            public void actionPerformed(ActionEvent event) {
                if (MainFraim.this.fileChooser == null) {
                    MainFraim.this.fileChooser = new JFileChooser();
                    MainFraim.this.fileChooser.setCurrentDirectory(new File("."));
                }

                if (MainFraim.this.fileChooser.showSaveDialog(MainFraim.this) == 0) {
                }

                MainFraim.this.saveToGraphicsFile(MainFraim.this.fileChooser.getSelectedFile());
            }
        };
        this.saveToGraphicsMenuItem = fileMenu.add(saveToGraphicsAction);
        this.saveToGraphicsMenuItem.setEnabled(false);
        Action afftarAction = new AbstractAction("О программе") {
            public void actionPerformed(ActionEvent event) {
                JLabel formImag = new JLabel();
                ImageIcon formul1 = new ImageIcon("Инесса.jpg");
                formImag.setIcon(formul1);
                Spravka spr = new Spravka();
                spr.setSize(370, 240);
                spr.setDefaultCloseOperation(1);
                spr.setVisible(true);
            }
        };
        this.searchValueMenuItem = helpMenu.add(afftarAction);
        this.searchValueMenuItem.setEnabled(true);
        JLabel labelInterFrom = new JLabel("X   от:");
        this.textFieldFrom = new JTextField("0.0", 10);
        this.textFieldFrom.setMaximumSize(this.textFieldFrom.getPreferredSize());
        JLabel labelInterTo = new JLabel("по:");
        this.textFieldTo = new JTextField("10", 10);
        this.textFieldTo.setMaximumSize(this.textFieldTo.getPreferredSize());
        JLabel labelInterStep = new JLabel("шаг:");
        this.textFieldStep = new JTextField("0.1", 10);
        this.textFieldStep.setMaximumSize(this.textFieldStep.getPreferredSize());
        Box hBoxField = Box.createHorizontalBox();
        hBoxField.setBorder(BorderFactory.createBevelBorder(1));
        hBoxField.add(Box.createHorizontalGlue());
        hBoxField.add(labelInterFrom);
        hBoxField.add(Box.createHorizontalStrut(10));
        hBoxField.add(this.textFieldFrom);
        hBoxField.add(Box.createHorizontalStrut(20));
        hBoxField.add(labelInterTo);
        hBoxField.add(Box.createHorizontalStrut(10));
        hBoxField.add(this.textFieldTo);
        hBoxField.add(Box.createHorizontalStrut(20));
        hBoxField.add(labelInterStep);
        hBoxField.add(Box.createHorizontalStrut(10));
        hBoxField.add(this.textFieldStep);
        hBoxField.add(Box.createHorizontalGlue());
        hBoxField.setPreferredSize(new Dimension((new Double(hBoxField.getMaximumSize().getWidth())).intValue(), (new Double(hBoxField.getMinimumSize().getHeight())).intValue() * 2));
        this.getContentPane().add(hBoxField, "North");
        JButton butonCalc = new JButton("Вычислить");
        butonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double from = Double.parseDouble(MainFraim.this.textFieldFrom.getText());
                    Double to = Double.parseDouble(MainFraim.this.textFieldTo.getText());
                    Double step = Double.parseDouble(MainFraim.this.textFieldStep.getText());
                    MainFraim.this.data = new GornerTable(from, to, step, MainFraim.this.coeff);
                    JTable table = new JTable(MainFraim.this.data);
                    table.setDefaultRenderer(Double.class, MainFraim.this.znak);
                    table.setRowHeight(30);
                    table.setDefaultRenderer(Boolean.class, MainFraim.this.strokaBOOL);
                    table.setRowHeight(30);
                    MainFraim.this.hBoxResult.removeAll();
                    MainFraim.this.hBoxResult.add(new JScrollPane(table));
                    MainFraim.this.getContentPane().validate();
                    MainFraim.this.saveToTextMenuItem.setEnabled(true);
                    MainFraim.this.saveToGraphicsMenuItem.setEnabled(true);
                    MainFraim.this.searchValueMenuItem.setEnabled(true);
                } catch (NumberFormatException var6) {
                    JOptionPane.showMessageDialog(MainFraim.this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
                }

            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFraim.this.textFieldFrom.setText("0.0");
                MainFraim.this.textFieldTo.setText("10");
                MainFraim.this.textFieldStep.setText("0.1");
                MainFraim.this.hBoxResult.removeAll();
                MainFraim.this.hBoxResult.add(new JPanel());
                MainFraim.this.saveToTextMenuItem.setEnabled(false);
                MainFraim.this.saveToGraphicsMenuItem.setEnabled(false);
                MainFraim.this.searchValueMenuItem.setEnabled(false);
                MainFraim.this.getContentPane().validate();
            }
        });
        Double from = Double.parseDouble(this.textFieldFrom.getText());
        Double to = Double.parseDouble(this.textFieldTo.getText());
        Double step = Double.parseDouble(this.textFieldStep.getText());
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.setBorder(BorderFactory.createBevelBorder(1));
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(butonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setPreferredSize(new Dimension((new Double(hboxButtons.getMaximumSize().getWidth())).intValue(), (new Double(hboxButtons.getMinimumSize().getHeight())).intValue() * 2));
        this.getContentPane().add(hboxButtons, "South");
        this.hBoxResult = Box.createHorizontalBox();
        this.hBoxResult.add(new JPanel());
        this.getContentPane().add(this.hBoxResult, "Center");
    }

    protected void saveToTextFile(File selectedFile) {
        try {
            PrintStream out = new PrintStream(selectedFile);
            out.println("Результаты многочлена по схеме Горнера");
            out.print("Многочлен: ");

            Double var10001;
            int i;
            for(i = 0; i < this.coeff.length; ++i) {
                var10001 = this.coeff[i];
                out.print(var10001 + "*X^" + (this.coeff.length - i - 1));
                if (i != this.coeff.length - 1) {
                    out.print(" + ");
                }
            }

            out.println("");
            var10001 = this.data.getFrom();
            out.println("Интервал от " + var10001 + " до " + this.data.getTo() + " с шагом " + this.data.getStep());
            out.println("====================================================");

            for(i = 0; i < this.data.getRowCount(); ++i) {
                Object var5 = this.data.getValueAt(i, 0);
                out.println("Значение в точке " + var5 + " равно " + this.data.getValueAt(i, 1));
            }

            out.close();
        } catch (FileNotFoundException var4) {
        }

    }

    protected void saveToGraphicsFile(File selctedFile) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(selctedFile));

            for(int i = 0; i < this.data.getRowCount(); ++i) {
                out.writeDouble((Double)this.data.getValueAt(i, 0));
                out.writeDouble((Double)this.data.getValueAt(i, 0));
            }

            out.close();
        } catch (Exception var4) {
        }

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Невозможно табулировать многочлен: не заданы коэффициенты!");
            System.exit(-1);
        }

        Double[] coeff = new Double[args.length];
        int i = 0;

        try {
            String[] var3 = args;
            int var4 = args.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String arg = var3[var5];
                coeff[i++] = Double.parseDouble(arg);
            }
        } catch (NumberFormatException var7) {
            System.out.println("Ошибка преобразования строки '" + args[i] + "' в число типа Double");
            System.exit(-2);
        }

        MainFraim frame = new MainFraim(coeff);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}
