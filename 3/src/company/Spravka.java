package company;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Spravka extends JFrame {
    Toolkit kit = Toolkit.getDefaultToolkit();
    Image f1;

    public Spravka() {
        this.f1 = this.kit.createImage("Инесса.jpg");
        this.setAlwaysOnTop(true);
        this.setLocation((this.kit.getScreenSize().width - 1) / 2 - 150, (this.kit.getScreenSize().height - 2) / 2 - 100);
        JPanel pane = new JPanel();
        pane.setSize(400, 200);
        pane.setVisible(true);
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();
        label2.setText("savchenko ");
        label3.setText("Лаба №3 Вариант A3");
        label4.setText("Группа №6");
        JButton buttonOk = new JButton("OK");
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Spravka.this.setVisible(false);
            }
        });
        Box contentBox = Box.createVerticalBox();
        Box hboxAll = Box.createHorizontalBox();
        hboxAll.add(Box.createHorizontalGlue());
        Box hboxlabel = Box.createVerticalBox();
        hboxAll.add(Box.createHorizontalGlue());
        hboxAll.add(hboxlabel.add(label));
        this.getContentPane().add(hboxAll, "West");
        Box hboxLab234 = Box.createVerticalBox();
        hboxLab234.add(Box.createVerticalStrut(50));
        hboxLab234.add(label2);
        hboxLab234.add(label3);
        hboxLab234.add(label4);
        this.getContentPane().add(hboxLab234, "East");
        Box hboxButton = Box.createHorizontalBox();
        hboxButton.add(Box.createVerticalStrut(50));
        hboxButton.add(Box.createHorizontalGlue());
        hboxButton.add(buttonOk);
        hboxButton.add(Box.createHorizontalGlue());
        this.getContentPane().add(hboxButton, "South");
        contentBox.add(hboxlabel);
        contentBox.add(hboxLab234);
        contentBox.add(hboxButton);
        pane.add(contentBox);
        this.getContentPane().add(pane);
    }
}
