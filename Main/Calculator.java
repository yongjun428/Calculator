package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 데이터베이스 연결을 관리하는 싱글톤 클래스입니다.
 *
 * @author Kim Yong Jun (igagima2@naver.com)
 * @version 2.5
 * @since 1.0
 *
 * @created 2024-10-28
 * @lastModified 2024-10-31
 *
 * @changelog
 * <ul>
 *   <li>2024-10-28: 최초 생성 및 테스트 (Kim Yong Jun )</li>
 *   </ul>>
 **/

public class Calculator extends JFrame implements ActionListener {
    JTextField text;
    String operator;
    double num1, num2, result;

    Calculator() {

        operator = "";

        /**
         *
         * @created 2024-10-28
         * @lastModified 2024-10-31
         *
         * @changelog
         * <ul>
         *   <li>2024-10-31: 실행시 초기화가 안되있던  오류 해결 (Kim Yong Jun )</li>
         * </ul>
         **/

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

        /**
         *
        * @created 2024-10-28
                * @lastModified 2024-10-31
                *
     * @changelog
     * <ul>
     *   <li>2024-10-31: TextFiled 사이즈 및 폰트 변경 (Kim Yong Jun )</li>
     * </ul>
     **/
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
        /**
         *
         * @created 2024-10-28
         * @lastModified 2024-10-31
         *
         * @changelog
         * <ul>
         *   <li>2024-10-31: Button Size 변경 및 Pont 변경 (Kim Yong Jun )</li>
         * </ul>
         **/

    }

    @Override

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {

            text.setText(text.getText() + command);
        } else if (command.equals("C")) {

            text.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.equals("=")) {

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
                operator = "";
            } catch (ArithmeticException ex) {
                text.setText("오류: " + ex.getMessage());
            }
        } else {

            if (!operator.isEmpty()) {

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
            } // @see


            /**
             *
             * @created 2024-10-28
             * @lastModified 2024-10-31
             *
             * @changelog
             * <ul>
             *   <li>2024-10-30: 버튼으로 구현만 되어있는 연산자에 기능을 추가 (Kim Yong Jun )</li>
             *   <li>2024-10-31: 연속적으로 계산 할 수 있는 기능을 추가 (Kim Yong Jun )</li>
             * </ul>
             **/




            operator = command;
            num1 = Double.parseDouble(text.getText());
            text.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
