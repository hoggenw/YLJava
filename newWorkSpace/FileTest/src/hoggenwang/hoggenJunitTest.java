package hoggenwang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class hoggenJunitTest {
	/**
	 * 测试方法 @Test注解 Junit 测试方法的规则 1）
	 */
	/**
	 * 写一个测试MathUtil的add方法的测试方法 注意： 给一个方法加上@Test注解，那么该方法就是一个测试方法（类似于一个main方法）
	 * 
	 * 1)Junit测试方法的规则： 1）测试方法一定在方法顶部要加上@Test注解 2）方法必须是public修饰，不能有返回值，不能有参数，可以抛出异常
	 * 2)Junit方法如何运行？ 1）运行一个方法： 双击对应的方法，右键Run As-》Junit Test (或者Outline视图，选择方法Run
	 * As-》Junit Test) 2）运行类的所有方法：双击类，右键Run As-》Junit Test 3）运行一个项目的所有方法：双击项目，右键Run
	 * As-》Junit Test 3）查看Junit方法的结果 查看Junit的导航条： 绿色： 代表测试通过了！ 红色： 代表测试不通过！
	 * 4)对结果进行判断 Assert断言类： Assert.assertEquals 判断是否相等，使用equals方法比较 assertSame
	 * 判断是否相等，使用==比较
	 */
	@Test
	public void testAdd() {
		int a = 6;
		int b = 10;
		hoggenClass hClass = new hoggenClass();
		int result = hClass.add(a, b);
		// if (result != 16) {
		// throw new RuntimeException(" hClass.add is wrong");
		// }
		assertEquals(16, result);
		// Assert.assertEquals(16, result);

		/**
		 * 得到枚举中所有值：每个枚举类型都有一个values()方法
		 */
		YLEnum[] arr = YLEnum.values();
		for (YLEnum score : arr) {
			System.out.println(score + " : " + score.getResult());

		}

	}

	@Test
	public void test() throws Exception {
		// 1)得到Class类的对象
		// 方式一：
		// Class class1 = FileModel.class;
		// 方式二： (依赖性比较低，只依赖字符串)
		Class class1 = Class.forName("hoggenwang.FileModel");
		// 方式三：
		// Class class2 = new FileModel().getClass();
		// 得到类名
		System.out.println(class1.getName());// 全名（包含包结构）
		System.out.println(class1.getSimpleName());// 简单名

		// 得到类的继承结构
		// 得到FileModel类的父类
		Class model = class1.getSuperclass();
		System.out.println(model.getSimpleName());

		// 得到类的接口
		Class[] interArray = class1.getInterfaces();
		System.out.println(interArray.length);
		for (Class class2 : interArray) {

			System.out.println(class2.getSimpleName());

		}

		/**
		 * 1)调用无参数的构造方法
		 */
		// 根据不同的参数列表获取不同的构造方法(Constructor)对象
		Constructor constructor = class1.getConstructor(null);

		// 2)通过Constructor类的方法构造对象
		Object obj = constructor.newInstance(null);
		/**
		 * getMethod() :获取类上的公共的方法（public） getDeclaredMethod(): 获取类上的所有方法（公共和私有的方法）
		 */
		/**
		 * 参数一： 方法名 参数二： 形式参数列表
		 */
		Method setName = class1.getMethod("setName", String.class);
		// 2)通过Method对象调用方法
		/**
		 * 参数一: 调用方法所需的对象 参数二：实际参数值
		 */
		setName.invoke(obj, "jack");

		Method getName = class1.getMethod("getName", null);
		// 调用方法，接收方法返回值
		Object result = getName.invoke(obj, null); // obj.getName();

		System.out.println(result);

		System.out.println("new:" + obj.getClass().getSimpleName());

		/**
		 * 调用有参数的构造方法
		 */

		Constructor con2 = class1.getConstructor(int.class, String.class);
		Object object = con2.newInstance(10, "hoggen");
		System.out.println(object.getClass().getSimpleName());
		System.out.println(object);

		/**
		 * getDeclaredField：得到任意修饰的属性（public/private） getField(): 得到公开的属性（public）
		 */
		Field name = class1.getDeclaredField("name");

		// 2)得到属性名
		System.out.println(name.getName());// name
		// 得到属性的类型
		System.out.println(name.getType()); // String

		/**
		 * 给属性赋值： 以前： s.name="eric" 现在：使用反射技术直接类似地直接给属性赋值,而不是调用setName方法赋值
		 */
		/**
		 * 参数一： 赋值给哪个对象 参数二： 赋值
		 */
		// 打破私有修饰符的限制
		name.setAccessible(true);// 忽略private修饰符
		name.set(object, "love");
		/**
		 * 获取属性值
		 */
		Object result3 = name.get(object);
		System.out.println(result3);

	}

}
