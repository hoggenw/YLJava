/**
 * Created by wangliugen on 2017/4/26.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class JavaServer {
    public  static  void  main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("启动服务器....");
            Socket socket = serverSocket.accept();
            System.out.println("client :" + socket.getInetAddress().getHostName() + " have already connet to server");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //读取客户端发送来的消息
            String message = bufferedReader.readLine();
            System.out.println("client said:" + message);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("hello client ! here is server");
            bufferedWriter.flush();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
