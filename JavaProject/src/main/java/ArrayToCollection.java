import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wangliugen on 2017/4/23.
 */
import  java.util.*;
import  java.io.*;
public class ArrayToCollection {

    public void setAndList() {
        setTest();
        listTest();

    }
    public  void  deleteSet() {
        setTest();
    }
    private  void  setTest() {
        Set<String> set = new HashSet<String>();
        set.add("JAVA");
        set.add("C");
        set.add("C++");
        // 重复数据添加失败
        set.add("JAVA");
        set.add("JAVASCRIPT");

        // 使用iterator遍历set集合
        System.out.println("使用iterator遍历set集合:");
        Iterator<String> it = set.iterator();

        while (it.hasNext()) {
            String value = it.next();
            System.out.println(value);
        }
        // 使用增强for循环遍历set集合
        System.out.println("使用for遍历set集合:");
        for(String s: set){
            System.out.println(s);
        }
        set.remove("C++");
        System.out.println("删除C++:");
        for(String s: set){
            System.out.println(s);
        }

    }
    private  void  listTest() {
        List<String> list = new ArrayList<String>();
        list.add("你");
        list.add("的");
        list.add("什么");
        list.add("是");
        list.add("what");
        // 使用iterator遍历
        System.out.println("使用iterator遍历:");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String value = it.next();
            System.out.println(value);
        }

        // 使用传统for循环进行遍历
        System.out.println("使用传统for循环进行遍历:");
        for (int i = 0, size = list.size(); i < size; i++) {
            String value = list.get(i);
            System.out.println(value);
        }

        // 使用增强for循环进行遍历
        System.out.println("使用增强for循环进行遍历:");
        for (String value : list) {
            System.out.println(value);
        }

    }

    public  void  shuffleList() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10 ; i++)
            list.add(new Integer(i));
//        System.out.println("打乱前:");
        System.out.println("翻转前:");
        System.out.println("翻转前:" + list);
        Collections.reverse(list);
        System.out.println("翻转后:" + list );
        System.out.println(list);

        for (int i = 0; i < 6 ; i++) {
            System.out.println("第" + i + "次打乱：");
            Collections.shuffle(list);
            System.out.println(list);
        }
    }

    public  void  hashSet() {
        System.out.println( "集合实例!\n" );
        HashSet hashSet = new HashSet();
        String str1 = "Yellow", str2 = "White", str3 =
                "Green", str4 = "Blue";
        Iterator iterator;
        hashSet.add(str1);
        hashSet.add(str2);
        hashSet.add(str3);
        hashSet.add(str4);
        System.out.print("集合数据: ");
        iterator = hashSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next() + " ");

        }
        System.out.println( "集合长度: " + hashSet.size());


    }

    public  void  hashMap() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("3","3st");
        hashMap.put("1","1st");
        hashMap.put("2","2st");

        Collection c1 = hashMap.values();
        Iterator itr = c1.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());

        }

    }

    public  void  changedToSet() {
        Set set = new TreeSet();
        String[] icons = creatArray();
        for (int i = 0; i < icons.length; i++ ) {
            set.add(icons[i]);
        }
        System.out.println(Collections.min(set));
        System.out.println(Collections.min(set,String.CASE_INSENSITIVE_ORDER));
        System.out.println(Collections.max(set));
        System.out.println(Collections.max(set,String.CASE_INSENSITIVE_ORDER));
    }

    public  void  changedToCollection() {
      String[] names = creatArray();

        List<String> list = Arrays.asList(names);
        System.out.println();
        for(String li: list){
            String str = li;
            System.out.print(str + " ");
        }

    }

    public String[] creatArray(){
        String[] coins = { "Penny", "nickel", "dime", "Quarter", "dollar" };
        String[] name = new String[5];
        for (int i = 0; i < 5; i++) {
            name[i] = String.valueOf(i);
        }
        System.out.println("names count :" + name.length);

        return  coins;

    }
}
