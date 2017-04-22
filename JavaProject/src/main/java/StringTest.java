import java.util.Locale;

/**
 * Created by wangliugen on 2017/4/20.
 */
public class StringTest {

    public  void stringConcatenate() {
        long startTime = System.currentTimeMillis();
        for(int i=0;i<5000;i++){
            String result = "This is"
                    + "testing the"
                    + "difference"+ "between"
                    + "String"+ "and"+ "StringBuffer";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("字符串连接"
                + " - 使用 + 操作符 : "
                + (endTime - startTime)+ " ms");
        long startTime1 = System.currentTimeMillis();
        for(int i=0;i<5000;i++){
            StringBuffer result = new StringBuffer();
            result.append("This is");
            result.append("testing the");
            result.append("difference");
            result.append("between");
            result.append("String");
            result.append("and");
            result.append("StringBuffer");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("字符串连接"
                + " - 使用 StringBuffer : "
                + (endTime1 - startTime1)+ " ms");
    }

    public  void  stringFormat() {
        double e = Math.E;
        System.out.format("%f%n",e);
        System.out.format(Locale.CHINA, "%-10.4f",e);
    }

    public  void  stringPerformance() {
        long startTime = System.currentTimeMillis();
        for(int i=0;i<50000;i++){
            String s1 = "hello";
            String s2 = "hello";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("通过 String 关键词创建字符串"
                + " : "+ (endTime - startTime)
                + " 毫秒" );
        long startTime1 = System.currentTimeMillis();
        for(int i=0;i<50000;i++){
            String s3 = new String("hello");
            String s4 = new String("hello");
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("通过 String 对象创建字符串"
                + " : " + (endTime1 - startTime1)
                + " 毫秒");
    }

    public  void  region() {
        String first_str = "Welcome to Microsoft";
        String second_str = "I work with microsoft";
        boolean match1 = first_str.
                regionMatches(11, second_str, 12, 9);
        boolean match2 = first_str.
                regionMatches(true, 11, second_str, 12, 9); //第一个参数 true 表示忽略大小写区别
        System.out.println("区分大小写返回值：" + match1);
        System.out.println("不区分大小写返回值：" + match2);
    }

    public  void  splitString() {
        String str = "i-love-you-memery";
        String[] temp;
        String deliment = "-";
        temp = str.toUpperCase().split(deliment);
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i]);
            System.out.println(" ");
        }
        //转大写 再转小写
        for (int i = 0; i < temp.length; i++) {
            System.out.println(temp[i].toLowerCase());
            System.out.println(" ");
        }
    }
    public void reverse(){
        String str = "Hello World";
        String reverse = new StringBuffer(str).reverse().toString();
        System.out.println("字符串反转前:"+str);
        System.out.println("字符串反转后:"+reverse);
    }

    public void  repalceTest(){
        String str = "Hello World";
        System.out.println(str);
        System.out.println(str.replace('H','W'));
        System.out.println(str.replaceFirst("He","Wa"));
        System.out.println(str.replaceAll("l","Ha"));
    }

    public  void  compareString() {
        String str = "Hello World";
        String anotherString = "hello world";
        Object objStr = str;
        System.out.println(str.compareTo(anotherString));
        System.out.println(str.compareToIgnoreCase(anotherString));
        System.out.println(str.compareTo(objStr.toString()));

    }

    public void searchLastString() {
        String strOrig = "Hello world ,Hello hoggen";
        //int lastIndex = strOrig.lastIndexOf("Hello");
        int index = strOrig.indexOf("Hello");
        if (index == -1){
            System.out.println("没有找到字符串 Hello");
        }else  {
            System.out.println("Hello 字符串最后出现的位置： "+ index);
        }
    }

    public  void removeCharAt(int index) {
        String strOrig = "Hello world ,Hello hoggen";
        System.out.println("删除前的字符串：" + strOrig);
        System.out.println("删除后的字符串: " + strOrig.substring(0,index) + strOrig.substring(index + 1));
    }
}
