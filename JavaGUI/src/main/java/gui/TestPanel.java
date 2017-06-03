package gui;

import javax.swing.*;
import java.awt.*;

public class TestPanel {
    public static void main(String[] args) {
        TestPanel application = new TestPanel();
        application.go();
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
}
