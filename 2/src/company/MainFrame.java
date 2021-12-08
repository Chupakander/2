package company;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

    @SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
    public class MainFrame extends JFrame {
        // Размеры окна приложения в виде констант
        private static final int WIDTH = 550;
        private static final int HEIGHT = 400;
        // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
        private JTextField textFieldX;
        private JTextField textFieldY;
        private JTextField textFieldZ;
        // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
        private JTextField textFieldResult;
        private JTextField textFieldMemory;
        // Группа радио-кнопок для обеспечения уникальности выделения в группе
        private ButtonGroup radioButtons = new ButtonGroup();

        // Контейнер для отображения радио-кнопок
        private Box hboxFormulaType = Box.createHorizontalBox();
        private int formulaId = 1;

        private Double result;
        private Double memory = new Double(0);
        // Формула №1 для рассчѐта

        public Double calculate1(Double x, Double y,Double z) {
            return (Math.sin(Math.PI*y*y)+Math.log(y*y))/(Math.sin(Math.PI*z*z)+Math.sin(x)+x*x+Math.log(z*z)+Math.pow(Math.E,Math.cos(z*x)));
        }
        // Формула №2 для рассчѐта
        public Double calculate2(Double x, Double y,Double z) {
            return ((x*x*y)/((Math.log(Math.pow(z,y)))+Math.pow(Math.cos(Math.pow(x,1/3)),2)));
        }
        ///////
        // Вспомогательный метод для добавления кнопок на панель
        private void addRadioButton(String buttonName, final int formulaId) {
            JRadioButton button = new JRadioButton(buttonName);
            button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ev)
                {
                    MainFrame.this.formulaId = formulaId;
                }
            });
            radioButtons.add(button);
            hboxFormulaType.add(button);
        }
        // Конструктор класса
        public MainFrame() {
            super("Вычисление формулы");
            setSize(WIDTH, HEIGHT);
            Toolkit kit = Toolkit.getDefaultToolkit();
// Отцентрировать окно приложения на экране
            setLocation((kit.getScreenSize().width - WIDTH)/2,
                    (kit.getScreenSize().height - HEIGHT)/2);
            hboxFormulaType.add(Box.createHorizontalGlue());//клей
            addRadioButton("Формула 1", 1);
            addRadioButton("Формула 2", 2);
            radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
            hboxFormulaType.add(Box.createHorizontalGlue());
            hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y
            JLabel labelForX = new JLabel("X:");
            textFieldX = new JTextField("0", 10);
            textFieldX.setMaximumSize(textFieldX.getPreferredSize());

            JLabel labelForY = new JLabel("Y:");
            textFieldY = new JTextField("0", 10);
            textFieldY.setMaximumSize(textFieldY.getPreferredSize());

            JLabel labelForZ = new JLabel("Z:");
            textFieldZ = new JTextField("0", 10);
            textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

            Box hboxVariables = Box.createHorizontalBox();
            hboxVariables.setBorder(
                    BorderFactory.createLineBorder(Color.RED));
            hboxVariables.add(Box.createHorizontalGlue());

            hboxVariables.add(labelForX);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldX);

            hboxVariables.add(labelForY);
            hboxVariables.add(Box.createHorizontalStrut(10));
            hboxVariables.add(textFieldY);

            hboxVariables.add(labelForZ);
            hboxVariables.add(Box.createHorizontalStrut(10));//распорка
            hboxVariables.add(textFieldZ);


            hboxVariables.add(Box.createHorizontalGlue());
// Создать область для вывода результата
            JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
            textFieldResult = new JTextField("0", 15);
            textFieldResult.setMaximumSize(
                    textFieldResult.getPreferredSize());
            Box hboxResult = Box.createHorizontalBox();
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.add(labelForResult);
            hboxResult.add(Box.createHorizontalStrut(10));
            hboxResult.add(textFieldResult);
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок

            JLabel labelForMemory = new JLabel("Память:");
            textFieldMemory = new JTextField("0",15);
            textFieldMemory.setMaximumSize(
                    textFieldMemory.getPreferredSize());

            Box hboxMemory = Box.createHorizontalBox();
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.add(labelForMemory);
            hboxResult.add(Box.createHorizontalStrut(10));
            hboxResult.add(textFieldMemory);
            hboxResult.add(Box.createHorizontalGlue());
            hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLACK));


            JButton buttonCalc = new JButton("Вычислить");
            buttonCalc.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    try {
                        Double x = Double.parseDouble(textFieldX.getText());
                        Double y = Double.parseDouble(textFieldY.getText());
                        Double z = Double.parseDouble(textFieldZ.getText());
                        if (formulaId == 1) {
                            result = calculate1(x, y, z);
                        }
                        else {
                            result = calculate2(x, y, z);
                        }
                        textFieldResult.setText(result.toString());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            JButton calculate = new JButton("M+");
            calculate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    try {
                        Double result = Double.parseDouble(textFieldResult.getText());
                        memory+=result;
                        textFieldMemory.setText(memory.toString());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });

            JButton buttonReset = new JButton("Очистить");
            buttonReset.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    textFieldX.setText("0");
                    textFieldY.setText("0");
                    textFieldZ.setText("0");
                    textFieldResult.setText("0");
                }
            });
//создание кнопки "MC"
            JButton buttonMC = new JButton("MC");
            buttonMC.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    memory = (double) 0;
                    textFieldMemory.setText("0");
                }
            });

            Box hboxButtons = Box.createHorizontalBox();
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.add(buttonCalc);
            hboxButtons.add(Box.createHorizontalStrut(10));
            hboxButtons.add(calculate);
            hboxButtons.add(Box.createHorizontalStrut(10));
            hboxButtons.add(buttonReset);
            hboxButtons.add(Box.createHorizontalStrut(10));
            hboxButtons.add(buttonMC);
            hboxButtons.add(Box.createHorizontalGlue());
            hboxButtons.setBorder(
                    BorderFactory.createLineBorder(Color.GREEN));
// Связать области воедино в компоновке BoxLayout
            Box contentBox = Box.createVerticalBox();
            contentBox.add(Box.createVerticalGlue());
            contentBox.add(hboxFormulaType);
            contentBox.add(hboxVariables);
            contentBox.add(hboxResult);
            contentBox.add(hboxMemory);
            contentBox.add(hboxButtons);
            contentBox.add(Box.createVerticalGlue());
            getContentPane().add(contentBox, BorderLayout.CENTER);
        }
        // Главный метод класса
        public static void main(String[] args) {
            MainFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }