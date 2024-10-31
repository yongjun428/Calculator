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
        // 초기화
        operator = "";
        num1 = num2 = result = 0;

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
        panelT.setLayout(null);

        text = new JTextField(30);
        text.setEditable(false);
        text.setBounds(0, 0, 285, 200);
        text.setHorizontalAlignment(JTextField.RIGHT); // 오른쪽 정렬
        text.setFont(new Font("Arial", Font.PLAIN, 30)); // 폰트 크기 조정
        panelT.setBackground(Color.pink);

        panelT.add(text);
        panelT.setPreferredSize(new Dimension(300, 200));
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
            button.setPreferredSize(new Dimension(75, 75)); // 버튼 크기 고정
            button.setFont(new Font("Arial", Font.PLAIN, 24)); // 버튼 폰트 크기 조정
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
            try {
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
                        if (num2 == 0) {
                            throw new ArithmeticException("0으로 나눌 수 없습니다.");
                        }
                        result = num1 / num2;
                        break;
                }
                text.setText(String.valueOf(result));
                operator = ""; // 연산자 초기화
            } catch (ArithmeticException ex) {
                text.setText("오류: " + ex.getMessage());
            }
        } else {
            // 연산자 버튼 클릭 시
            if (!operator.isEmpty()) {
                // 이미 연산자가 설정되어 있으면 계산 후 새로운 연산자로 변경
                num2 = Double.parseDouble(text.getText());
                try {
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
                            if (num2 == 0) {
                                throw new ArithmeticException("0으로 나눌 수 없습니다.");
                            }
                            result = num1 / num2;
                            break;
                    }
                    text.setText(String.valueOf(result));
                } catch (ArithmeticException ex) {
                    text.setText("오류: " + ex.getMessage());
                }
            }

            // 새로운 연산자 설정
            operator = command;
            num1 = Double.parseDouble(text.getText());
            text.setText(""); // 텍스트 필드 초기화
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
