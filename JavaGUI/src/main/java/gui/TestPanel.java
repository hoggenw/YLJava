package gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPanel implements ListSelectionListener {
    JTextField textField;
    JTextArea textArea;
    JCheckBox box1;
    JCheckBox box2;
    JList<String> list;
    public static void main(String[] args) {
        TestPanel application = new TestPanel();
       // application.go();
       // application.textArea();
      //  application.textFeild();
       // application.checkBox();
        application.list();


    }

    public void go() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        panel.setBackground(Color.darkGray);

        //changed
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));


        JButton labelButton = new JButton("Change Label");
        panel.add(labelButton);
//        labelButton.addActionListener( new Application.LabelListener());
//
        JButton colorButton = new JButton("Change Color");
        panel.add(colorButton);
        frame.getContentPane().add(BorderLayout.EAST,panel);
//        colorButton.addActionListener( new Application.ColorListener());
//
//        label = new JLabel("I'm a lable");


//        frame.getContentPane().add(BorderLayout.SOUTH,colorButton);
//        frame.getContentPane().add(BorderLayout.CENTER,drawnPanel);
//        frame.getContentPane().add(BorderLayout.EAST,labelButton);
//        frame.getContentPane().add(BorderLayout.WEST,label);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);
        frame.setVisible(true);

    }
    public  void textFeild() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        textField = new JTextField(20);
        textField.setText("I'm the begin");
        textField.requestFocus();
        panel.add(textField);
        frame.getContentPane().add(BorderLayout.CENTER,panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);
        frame.setVisible(true);

    }

    public  void textArea() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JButton button = new JButton("click it");
        button.addActionListener(new ColorListener());
        textArea = new JTextArea(10,20);
        textArea.setLineWrap(true);//自动换行

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane);
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.getContentPane().add(BorderLayout.SOUTH,button);





        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);
        frame.setVisible(true);

    }

    public  void checkBox() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        box1 = new JCheckBox("to 1");
        box2 = new JCheckBox("to 2");

        panel.add(box1);
        panel.add(box2);


        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);
        frame.setVisible(true);

    }

    public  void list() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        String[] listS = {"1","3","4","5"};
        list = new JList<>(listS);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        list.setVisibleRowCount(3);//设置显示行数
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);//限制单选
        list.addListSelectionListener(this);


        panel.add(scrollPane);

        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);
        frame.setVisible(true);

    }
    public void valueChanged(ListSelectionEvent lse) {
        if (!lse.getValueIsAdjusting()) {
            String selction = (String) list.getSelectedValue();
            System.out.print(selction);
        }
    }

    public class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
         textArea.append("button cliked \n");
        }
    }
    public class TextFeildListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println(textField.getText());
            textField.setText("");
        }
    }




}
