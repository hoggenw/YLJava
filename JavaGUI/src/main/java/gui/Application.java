package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application  {
    JFrame frame;
    JLabel label;
   // JButton button;
    public static void main(String[] args) {
        Application application = new Application();
        application.go();
    }
    public void go() {
        frame = new JFrame();
        JButton labelButton = new JButton("Change Label");
        labelButton.addActionListener( new LabelListener());

        JButton colorButton = new JButton("Change Color");
        colorButton.addActionListener( new ColorListener());

        label = new JLabel("I'm a lable");
        MyDrawnPanel drawnPanel = new MyDrawnPanel();

        frame.getContentPane().add(BorderLayout.SOUTH,colorButton);
        frame.getContentPane().add(BorderLayout.CENTER,drawnPanel);
        frame.getContentPane().add(BorderLayout.EAST,labelButton);
        frame.getContentPane().add(BorderLayout.WEST,label);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);
        frame.setVisible(true);
    }

//    public void actionPerformed(ActionEvent event) {
//        button.setText("I've been cliked");
//    }
    public class LabelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("ouch");
        }
    }
    public class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.repaint();
        }
    }
}


