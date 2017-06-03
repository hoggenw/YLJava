package gui;

import javax.swing.*;
import java.awt.*;

public class MyDrawnPanel extends JPanel {
    public void paintComponent(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(20,50,100,100);
       // g.fillOval(x,y,100,100);
    }
}
