package Main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    JTextField text;
    String operator; // 연산자 저장
    double num1, num2, result; // 숫자 및 결과 저장

    Calculator() {
        setTitle("계산기");
        setSize(300, 500);
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
        text = new JTextField(30);
        text.setEditable(false);
        text.setHorizontalAlignment(JTextField.LEFT);
        panelT.setBackground(Color.orange);
        panelT.setForeground(Color.pink);


        panelT.add(text);
        add(panelT, BorderLayout.NORTH);
    }

    void Num() {
        JPanel panelN = new JPanel();
        panelN.setLayout(new GridLayout(4, 4, 2, 2));
        add(panelN, BorderLayout.CENTER);
        panelN.setBackground(Color.BLACK);

        String[] NUM = {
                "7", "8", "9", "+",
                "4", "5", "6", "-",
                "1", "2", "3", "/",
                "0", "=", "C", "*"
        };

        for (String label : NUM) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            panelN.add(button);
            button.setBackground(Color.darkGray);
            button.setForeground(Color.white);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            // 숫자 버튼 클릭 시
            text.setText(text.getText() + command);
        } else if (command.equals("C")) {
            // 초기화 버튼 클릭 시
            text.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("=")) {
            // 계산 버튼 클릭 시
            num2 = Double.parseDouble(text.getText());
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }
            text.setText(String.valueOf(result));
        } else {

            if (!operator.isEmpty()) {
                return;
            }
            operator = command;
            num1 = Double.parseDouble(text.getText());
            text.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}