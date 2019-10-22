package gui;

import javax.swing.*;
import java.awt.*;

public class SimpleAnimation {
    int x = 70;
    int y = 70;
    public static void main(String[] args) {
        SimpleAnimation application = new SimpleAnimation();
        application.go();
    }

    public void go() {
        JFrame frame = new JFrame();
//        JButton labelButton = new JButton("Change Label");
//        labelButton.addActionListener( new Application.LabelListener());
//
//        JButton colorButton = new JButton("Change Color");
//        colorButton.addActionListener( new Application.ColorListener());
//
//        label = new JLabel("I'm a lable");
        ODrawnPanel oDrawnPanel= new  ODrawnPanel ();
        frame.getContentPane().add(oDrawnPanel);

//        frame.getContentPane().add(BorderLayout.SOUTH,colorButton);
//        frame.getContentPane().add(BorderLayout.CENTER,drawnPanel);
//        frame.getContentPane().add(BorderLayout.EAST,labelButton);
//        frame.getContentPane().add(BorderLayout.WEST,label);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(300,300);
        frame.setVisible(true);

        for (int i = 0; i < 130; i ++) {
            x++;
            y++;
            oDrawnPanel.repaint();
            try {
                Thread.sleep(50);
            }catch (Exception ex){

            }
        }
    }

    public class ODrawnPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.orange);
             g.fillOval(x,y,40,40);
        }
    }
}
