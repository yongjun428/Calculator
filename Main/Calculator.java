package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    JTextField text;




    Calculator() {
        setTitle("계산기");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Case();
        Result();
        Num();

        setVisible(true);

    }
    void Case() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        add(panel);
    }

    void Result() {
        JPanel panelT = new JPanel();

        text = new JTextField(10);
        text.setEditable(false);

        panelT.add(text);
        add(text, BorderLayout.NORTH);

    }
    void Num() {
        JPanel panelN = new JPanel();
        panelN.setLayout(new GridLayout(4,4,2,2));
        add(panelN, BorderLayout.CENTER);

        String[] NUM = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "/",
                "0", "=", "C", "*"
        };

        for (String label : NUM) {
            JButton button = new JButton (label);

            panelN.add(button);
        }

        setVisible(true);

    }
    public static void main(String[] args) {

        new Calculator();
    }

}
