package socket;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClientA {
    JTextField outgoing;
    JTextArea incoming;
    BufferedReader reader;
    PrintWriter writer;
    Socket socket;

    public  void  go() {
        JFrame frame = new JFrame("simple client");
        JPanel panel = new JPanel();
        incoming = new JTextArea(15,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(incoming);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        outgoing = new JTextField(20);
        JButton labelButton = new JButton("Send");

//        panel.setBackground(Color.darkGray);


        labelButton.addActionListener( new BurronListener());
        panel.add(scrollPane);
        panel.add(labelButton);
        panel.add(outgoing);
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        setUpNetworking();

        Thread readerThread = new Thread(new IncomingReder());
        readerThread.start();
        frame.setSize(400,500);
        frame.setVisible(true);


    }

    private void setUpNetworking() {
        try {
            socket = new Socket("127.0.0.1",5000);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("networking established");
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class BurronListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                writer.println(outgoing.getText());//开始写数据，printl会把信息送到服务器？
                writer.flush();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args) {
        new SimpleChatClientA().go();
    }


    public class IncomingReder implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message );
                    incoming.append(message + "\n");

                }

            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
