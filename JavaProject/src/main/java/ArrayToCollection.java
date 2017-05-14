import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wangliugen on 2017/4/23.
 */
import  java.util.*;
import  java.io.*;

import apple.laf.JRSUIUtils;

public class ArrayToCollection {

    public  void  hashTable() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("1", "One");
        hashtable.put("2", "Two");
        hashtable.put("3", "Three");
        //Enumeration e = hashtable.keys();
        Enumeration e = hashtable.elements();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }

    }

    public  void  rotateList() {
        List list = Arrays.asList("one two three four five six ".split(" "));
        System.out.println("List :" + list);
        Collections.rotate(list,3);
        System.out.println("最大值: " + Collections.max(list));
        System.out.println("最小值: " + Collections.min(list));
        System.out.println("rotate: " + list);
        Collections.replaceAll(list,"one","hundrea");
        System.out.println("relaceAll: " + list);
    }

    public  void  mapValueAndKey() {
        System.out.println("TreeMap example!");
        TreeMap tMap = new TreeMap();

        tMap.put(1, "Sunday");
        tMap.put(2, "Monday");
        tMap.put(3, "Tuesday");
        tMap.put(4, "Wednesday");
        tMap.put(5, "Thursday");
        tMap.put(6, "Friday");
        tMap.put(7, "Saturday");
        System.out.println("TreeMap key values: " + tMap.keySet());
        System.out.println("TreeMap values:  " + tMap.values());
        System.out.println("where key is equal to 5,the value is : " + tMap.get(5));
        System.out.println("first key is:" + tMap.firstKey() + "value is:" + tMap.get(tMap.firstKey()));
        System.out.println("last key is:" + tMap.lastKey() + "value is:" + tMap.get(tMap.lastKey()));
        System.out.println("移除第一个数据: " + tMap.remove(tMap.firstKey()));
        System.out.println("now TreeMap key values: " + tMap.keySet());
        System.out.println("now TreeMap values:  " + tMap.values());
        System.out.println("移除最后一个数据: " + tMap.remove(tMap.lastKey()));
        System.out.println("now TreeMap values:  " + tMap.values());

    }
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
        Lin
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
