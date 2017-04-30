/**
 * Created by wangliugen on 2017/4/28.
 */
public class Doors100 {
    static boolean[] array = creatDoors();

    public  void showTheRusult(){
        for (int i = 1; i <= 100; i++){
            changedArrayByNumber(i);
        }
        for (int i = 0; i < 100; i++){
            System.out.println("number: "+ (i+1)  + " this is over:" + array[i]);
        }


    }

    public  static void changedArrayByNumber(int index){
        for (int i = 0; i < 100; i++){
            if ((i + 1)% index == 0){
                array[i] = !array[i];
            }
        }
    }

    public static boolean[] creatDoors(){
        boolean[] array = new boolean[100];
        for (int i = 0; i < 100; i ++ ){
            array[i] = false;
        }
        return  array;
    }
}
