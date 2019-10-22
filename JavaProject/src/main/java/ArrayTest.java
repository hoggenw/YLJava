import static java.util.Arrays.asList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wangliugen on 2017/4/20.
 */
public class ArrayTest {

    public  void  fillArray() {
        int array[] = new int[6];

        Arrays.fill(array,100);
        printArray("填充",array);
        Arrays.fill(array,3,6,50);
        printArray("更改填充",array);
        int[] extended = new int[8];
        extended[6] = 66;
        extended[7] = 88;
        System.arraycopy(array,0,extended,0,array.length);
        printArray("extended",extended);


    }

    public  void  reverseArray() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        System.out.println("反转前排序: " + arrayList);
        Collections.reverse(arrayList);
        System.out.println("反转后排序: " + arrayList);
        arrayList.remove("a");
        System.out.println("数组删除元素后："+ arrayList);

        ArrayList<String> array = new ArrayList();
        array.add("a");
        array.add("b");
        array.add("c");
        array.add("d");
        array.add("e");

        System.out.println("array 是否包含字符串common2? ： "
                +array.contains("a"));

        System.out.println("Array 是否包含数组 arrayList ："
        + array.containsAll(arrayList));
        String[] arra1 = {"sd","d","f"};
        String[] arra2 = {"a","s"};
        List<String> strings = asList("AD", "FDS");
        String[] result =  uion(arra1,arra2);
        System.out.println("Array 并集 ：");






//        array.removeAll(arrayList);
//        System.out.println("差集："+ array);
//
//        array.retainAll(arrayList);
//        System.out.println("交集："+ array);

    }

    //两个字符串数组的并集，利用set的元素唯一性
    public  static  String[] uion(String[] arr1, String[] arr2) {
        Set<String> set = new HashSet<>();
        for (String str: arr1){
            set.add(str);
        }
        for (String str: arr2) {
            set.add(str);
        }
        String[] result = {};
        return set.toArray(result);
    }


    public  void  sortArray() {
        int array[] =  { 5,2, -2, 6, -3, 8, 0, -7, -9, 4,-10 };
        int index1 = Arrays.binarySearch(array,1);

        System.out.println("元素 2  在第 " + index1 + " 个位置");
        Arrays.sort(array);
        printArray("数组排序结果为： ",array);
        int index = Arrays.binarySearch(array,2);
        System.out.println("元素 2  在第 " + index + " 个位置");

        Integer[] numbers = { 5,2, -2, 6, -3, 8, 0, -7, -9, 4,-10 };
        int min = (int) Collections.min(asList(numbers));
        int max = (int) Collections.max(asList(numbers));
        System.out.println("最小值: " + min);
        System.out.println("最大值: " + max);



//        String[][] data = new String[2][5];
//        System.out.println("第一维数组长度: " + data.length);
//        System.out.println("第二维数组长度: " + data[0].length);

    }



    public  static  void printArray(String message, int array[]) {
        System.out.println(message + ": [length: " + array.length + "]");
        for ( int i = 0; i < array.length; i++) {
            if (i != 0){
                System.out.print(", ");
            }
            System.out.print(array[i]);
        }
        System.out.println();

    }
}
