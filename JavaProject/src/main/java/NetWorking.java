/**
 * Created by wangliugen on 2017/4/25.
 */
import java.net.*;
import java.net.UnknownHostException;
import java.io.*;
import java.net.InetAddress;

public class NetWorking {

    public void getSize() throws IOException {
        int size;
        URL url = new URL("http://www.runoob.com/wp-content/themes/runoob/assets/img/newlogo.png");
        URLConnection connection = url.openConnection();
        size = connection.getContentLength();
        if (size < 0) {
            System.out.println("failer");
        }else
            System.out.println("file size is :" + size + "bytes");
        connection.getInputStream().close();
    }

    public void getSelfId() throws UnknownHostException {
            InetAddress address = null;
            try {
                address = InetAddress.getLocalHost();
            }catch (UnknownHostException e ) {

            }
            System.out.println("Local HostAddress:" + address.getHostAddress());
            String hostname = address.getHostName();
            System.out.println("Local host name: "+hostname);
    }

    public void checkHost(String[] args) {
        Socket socket;
        String host = "localhost";
        if (args.length > 0) {
            System.out.println("come in");
            host = args[0];
        }
        for (int i = 0; i < 1024; i++) {
            try {
                System.out.println("查看" + i);
                socket = new Socket(host,i);
                System.out.println("端口 " + i + " 已被使用");

            }catch (UnknownHostException e){
                System.out.println("Exception occured"+ e);
                break;
            }catch (IOException e){

            }
        }
    }

    public void getID() {
        InetAddress address = null;
        try {
            address = InetAddress.getByName("www.baidu.com");
        }catch (UnknownHostException e ) {
            System.exit(2);
        }
        System.out.println(address.getHostName() + " = " + address.getHostAddress());
        System.exit(0);
    }
}
