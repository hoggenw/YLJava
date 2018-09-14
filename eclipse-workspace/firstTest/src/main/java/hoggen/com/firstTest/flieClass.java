package hoggen.com.firstTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class flieClass {

	public flieClass() {
		// TODO Auto-generated constructor stub
	}

	public void writeFileTest() throws FileNotFoundException, IOException {
		File file = new File("/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/a.txt");
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write('a');
		fileOutputStream.write('z');
		fileOutputStream.write('i');
		fileOutputStream.write('t');
		fileOutputStream.write('c');
		fileOutputStream.write('a');
		fileOutputStream.write('s');
		fileOutputStream.write('t');

		// 2：通过流向文件写数据
		byte[] byt = "java".getBytes();
		fileOutputStream.write(byt);

		fileOutputStream.close();
	}

	public void readFileTest() throws FileNotFoundException, IOException {
		// 创建文件对象
		File file = new File("/Users/wangliugen/Desktop/SelfProject/YLJava/eclipse-workspace/a.txt");
		// 创建文件输入流
		FileInputStream fis = new FileInputStream(file);
		// 有对多长，就读多少字节。
		// for (int i = 0; i < file.length(); i++) {
		// System.out.print((char) fis.read());
		// }

		int len;
		while ((len = fis.read()) != -1) {
			System.out.println((char) len);
		}
		fis.close();
	}

}
