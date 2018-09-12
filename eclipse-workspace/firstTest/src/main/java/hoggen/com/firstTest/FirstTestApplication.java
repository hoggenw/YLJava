package hoggen.com.firstTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.bytebuddy.asm.Advice.Return;

@SpringBootApplication
public class FirstTestApplication {

	public static void main(String[] args) {
//		System.out.println("hello java");
//		 System.out.println(10);
//		 System.out.println(010);
//		 System.out.println(0x10);
//		 System.out.println(Integer.toBinaryString(10));
//		 System.out.println('a'+1);
		
//		outer:for(int i = 0; i <4; i++) {
//			inner:for (int j = 0; j < 5; j++) {
//				System.out.print("*");
//				break outer;
//			}
//			System.out.print("s\r\n");
//		}
		drawRect(10, 10);
		SpringApplication.run(FirstTestApplication.class, args);
	}
	
	public static int getResult(int x) {
		System.out.println( x*8);
		return x *8;
	}
	
	public static void drawRect(int width, int height) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				System.out.print("*");
			}
			System.out.print("\n");
		}
	}
}
