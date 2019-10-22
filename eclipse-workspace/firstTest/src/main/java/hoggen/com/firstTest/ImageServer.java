package hoggen.com.firstTest;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 
 * 
 * 
 * */

public class ImageServer extends Thread {

	public Socket socket;

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			// 获取socket输出字节流
			OutputStream socekyOut = socket.getOutputStream();
			// 获取图片输入流，读取该图片数据，把图片数据写入个客户端
			FileInputStream fileInputStream = new FileInputStream(
					"/Users/wangliugen/Desktop/存储资料图片/1A6EC588-4A02-46CF-BB56-46D549F07BD8.png");
			byte[] bug = new byte[1024];
			int length = 0;
			while ((length = fileInputStream.read(bug)) != -1) {
				socekyOut.write(bug, 0, length);

			}
			String ip = socket.getInetAddress().getHostAddress();// 获取对方id地址
			System.out.println("ip : " + ip);
			fileInputStream.close();
			socket.close();
		} catch (Exception e) {

		}

		super.run();
	}

	public ImageServer(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;

	}

}
