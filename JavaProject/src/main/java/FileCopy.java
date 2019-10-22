/**
 * Created by wangliugen on 2017/4/18.
 */
import  java.io.*;
import  java.io.FileNotFoundException;

public class FileCopy {
    //在java中的finally关键一般与try一起使用，在程序进入try块之后，无论程序是因为异常而中止或其它方式返回终止的，finally块的内容一定会被执行
    public  void  fileTry() throws IOException {
        FileInputStream in = null;
        FileOutputStream out =  null;

        try {
            try {
                in = new FileInputStream("input.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                out = new FileOutputStream("output.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }

        }finally {
            if (in != null) {
                in.close();;
            }
            if (out != null) {
                out.close();
            }
        }
    }

    public  void  filedeal() throws  IOException {
       InputStreamReader cin = null;

       try {
           cin = new InputStreamReader(System.in);
           System.out.println("Enter characters, 'q' to quit");
           char c;
           do {
               c = (char) cin.read();
               System.out.print(c);
           }while (c != 'q');

       }finally {
           if (cin != null) {
               cin.close();
           }
       }
    }
}















