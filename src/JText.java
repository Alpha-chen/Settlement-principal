import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.ParseException;

public class JText {
    private JTextField lixiPanelJTF = new JTextField("0.0005", 30);    // 创建文本行组件, 30 列
    private JTextField moneyJTF = new JTextField(30);   // 创建密码文本行组件, 30 列
    private JTextArea dateJTA = new JTextArea(20, 30);               // 创建文本区组件,10行，30列
    private JScrollPane jsp = new JScrollPane(dateJTA);                    // 创建滚动窗格，其显示内容是文本区对象

    private JLabel jPanel;
    private JLabel moneyPanel;
    private JLabel lixiPanel;

    private JLabel dateListPanel;
    private JLabel dateListPanelTip;
    private JLabel guiFanPanel;
    private JTextArea guifanArea;               // 创建文本区组件,10行，30列
    private JScrollPane guifanAreasp;


    private JLabel resultLabel;
    private JScrollPane resultAreasp;
    private JTextArea resultArea;               // 创建文本区组件,10行，30列

    private JButton jButton;

    /**29000 0000
     * 2018-09-24,2018-09-27,2018-11-01,2019-03-28,2019-04-02,2019-06-28
     */
    /**
     * 10000000.00,48480000.00,44398168.77,1431952.46,28061435.00
     */
    String[] array = {"2018-09-24", "2018-09-27", "2018-11-01"
            , "2019-03-28", "2019-04-02", "2019-06-28"};
    String[] money = {"10000000.00", "48480000.00"
            , "44398168.77", "1431952.46", "28061435.00"
    };
    BigDecimal a = new BigDecimal("300000000");
    BigDecimal b = new BigDecimal("0.0005");
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String date = dateJTA.getText();
                String[] tempArray = date.split(",");
                String moneyStr = guifanArea.getText();
                String[] moneyArray = moneyStr.split(",");
                resultArea.setText(new GetMoney().getMoney(tempArray, moneyArray, new BigDecimal(moneyJTF.getText()), new BigDecimal(lixiPanelJTF.getText())));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    };

    public JText() {
        initWindows();
    }

    private void initWindows() {
        moneyPanel = new JLabel("本金:");
        lixiPanel = new JLabel("利息比例(如0.0005):");
        dateListPanel = new JLabel("还款节点:");
        dateListPanelTip = new JLabel("(格式2019-01-01多个日期','分隔,归还金额输入格式一致)");
        guiFanPanel = new JLabel("归还金额:");

        guifanArea = new JTextArea(20, 30);
        guifanArea.setLineWrap(true); // 设置自动换行

        guifanAreasp = new JScrollPane(guifanArea);
        jButton = new JButton("计算");


        resultLabel = new JLabel("结果:");
        resultArea = new JTextArea(100, 30);
        resultArea.setLineWrap(true); // 设置自动换行

        resultAreasp = new JScrollPane(resultArea);

    }

    public void display() {

        // 布局
        JFrame f = new JFrame("结算本金");
        f.setBounds(200, 150, 650, 800);
        f.setLayout(null);

        dateJTA.setLineWrap(true); // 设置自动换行

        moneyJTF.setBounds(60, 10, 140, 20);
        lixiPanelJTF.setBounds(130, 40, 140, 20);
        jsp.setBounds(80, 70, 500, 100);

        moneyPanel.setBounds(20, 10, 60, 20);
        lixiPanel.setBounds(20, 40, 130, 20);
        dateListPanel.setBounds(20, 70, 80, 20);
        dateListPanelTip.setBounds(20, 180, 300, 20);
        guiFanPanel.setBounds(20, 200, 80, 20);
        guifanAreasp.setBounds(80, 200, 500, 100);
        jButton.setBounds(300, 320, 60, 40);


        resultLabel.setBounds(20, 370, 60, 20);
        resultAreasp.setBounds(80, 370, 500, 300);


        // 把组件添加进窗口f中
        f.add(moneyJTF);
        f.add(lixiPanelJTF);
        f.add(jsp);


        f.add(moneyPanel);
        f.add(lixiPanel);
        f.add(dateListPanel);
        f.add(dateListPanelTip);
        f.add(guiFanPanel);
        f.add(guifanAreasp);
        f.add(jButton);
        f.add(resultLabel);
        f.add(resultAreasp);
        jButton.addActionListener(actionListener);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        (new JText()).display();
    }
}