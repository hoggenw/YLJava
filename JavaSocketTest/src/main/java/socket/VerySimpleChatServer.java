package socket;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class VerySimpleChatServer {

    ArrayList clientOutputStream;

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }
    public  void  go() {
       clientOutputStream = new ArrayList();
       try {
           ServerSocket serverSocket = new ServerSocket(5000);
           while (true) {
               Socket clientSocket = serverSocket.accept();
               PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
               clientOutputStream.add(writer);

           }
       }catch (Exception ex) {
           ex.printStackTrace();
       }

    }

    public void tellEverone(String messgae) {
        Iterator it = clientOutputStream.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(messgae);
                writer.flush();

            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public class ClientHander implements Runnable {
        BufferedReader reader;
        Socket socket;

        public ClientHander(Socket clientSocket) {
            try {
                socket = clientSocket;
                InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
                reader = new BufferedReader(isReader);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        public  void  run() {
            String message;
            try {
                while ((message = reader.readLine()) != null){
                    System.out.println("server read " + socket.toString() +"  " + message);
                    tellEverone(message);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
