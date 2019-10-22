/**
 * Created by wangliugen on 2017/4/20.
 */
import  java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class DateTest {

    public void DateFormat() {
        Date date = new Date();
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println("当前时间： " + sdf.format(date));

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int doy = cal.get(Calendar.DAY_OF_YEAR);
        System.out.println("当期时间: " + cal.getTime());
        System.out.println("日期: " + day);
        System.out.println("月份: " + month);
        System.out.println("年份: " + year);
        System.out.println("一周的第几天: " + dow);  // 星期日为一周的第一天输出为 1，星期一输出为 2，以此类推
        System.out.println("一月中的第几天: " + dom);
        System.out.println("一年的第几天: " + doy);

        Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
        SimpleDateFormat sdfg=new SimpleDateFormat("yyyy-MM-dd");
        String sd = sdfg.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
        System.out.println(sd);

        String curDir = System.getProperty("user.dir");
        System.out.println("你当前的工作目录为 :" + curDir);


    }

}
