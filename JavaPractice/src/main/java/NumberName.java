import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangliugen on 2017/5/6.
 */
public class NumberName {

    public void  numberNameTest() {

        System.out.println("请输入您的数字!");
        Scanner scanner = new Scanner(System.in);
        String numberString = pickNumberFromString(scanner.next());
        String numberName = changedToNumberName(numberString);
        System.out.println("numberName is : " + numberName);

    }

    private String changedToNumberName(String numberString){
        String[] foo = {" thousand"," million"," billion"};

        ArrayList<String> arrayList = cutOutNumberString(numberString);
        int totalIndex =  arrayList.size()-1;
        String[] subsection = new String[totalIndex];
        String returnString = "";

        if (arrayList.size() > 1){
            for (int i = totalIndex - 1; i >= 0;i--){
                subsection[i] = foo[safeNumber(totalIndex - 1 - i)];
            }
            for (int i = 0; i <= totalIndex; i ++){
                if (i != totalIndex){
                    returnString = returnString + arrayList.get(i) + subsection[i];
                }else {
                    returnString = returnString + arrayList.get(i);
                }

            }
              return  returnString;
        }else {
            returnString = returnString + arrayList.get(0);
            return returnString;
        }


    }

    private int safeNumber(int number) {
        return  number % 3;
    }

    private ArrayList<String> cutOutNumberString(String numberString) {
        Hashtable dictionary = new Hashtable();
        String dealString = numberString;
        int length =  dealString.length();
        int lastIndex = length ;
        int number = length / 3;
        System.out.println("number:  " + number);
        int remainder = length % 3;

        String[] cutOutnumberString;

        if (remainder == 0) {
            if (number <= 0) {
                number = 1;
            }
            cutOutnumberString = new String[number];
            for (int i = number - 1; i >= 0; i--) {
                int endIndex = lastIndex - (number - i - 1) * 3;
                int startIndex = endIndex - 3 > 0 ? endIndex - 3 : 0;
                cutOutnumberString[i] = dealString.substring(startIndex, endIndex);
            }
        }else {
            cutOutnumberString = new String[number + 1];
            for (int i = number + 1 - 1; i >= 0; i--) {
                int endIndex = lastIndex - (number + 1 - i - 1) * 3;
                int startIndex = endIndex - 3 > 0 ? endIndex - 3 : 0;
                cutOutnumberString[i] = dealString.substring(startIndex, endIndex);
            }

        }
        ArrayList<String> arrayList = new ArrayList();
        for (int i = 0 ; i < cutOutnumberString.length; i++) {
            String numberString1 = cutOutnumberString[i];
            if (i == cutOutnumberString.length -1){
                arrayList.add(changedPartname(numberString1,true));
            }else {
                arrayList.add(changedPartname(numberString1, false));
            }
        }

        return  arrayList;
    }

    private String changedPartname(String input,Boolean showAnd){
        Integer number = Integer.parseInt(input);
        int hundredNumber = number / 100;
        int tenNUmber = (number %100) / 10 ;
        int unitNumber = number % 10;
        String returnString = "";
        if (hundredNumber != 0){
            if (showAnd) {
                returnString = returnUnitNumberName(hundredNumber) + " hundred and";
                if (tenNUmber == 0 && unitNumber == 0){
                    returnString = returnUnitNumberName(hundredNumber) + " hundred";
                    return  returnString;
                }
            }else {
                returnString = returnUnitNumberName(hundredNumber) + " hundred";
                if (tenNUmber == 0 && unitNumber == 0){
                    returnString = returnUnitNumberName(hundredNumber) + " hundred";
                    return  returnString;
                }
            }

        }

        if (tenNUmber == 1) {
            returnString = returnString + returntenNumberName(number %100) ;
            return  returnString;
        }else {
            returnString = returnString + returntenTohundredNumberName(tenNUmber);
        }
        returnString = returnString + returnUnitNumberName(unitNumber);
        return  returnString;
    }

    static  String  pickNumberFromString(String input) {
        String regEX = "[^0-9]";
        Pattern p = Pattern.compile(regEX);
        Matcher matcher = p.matcher(input);
        Long number = Long.parseLong(matcher.replaceAll("").trim());
        return   number.toString();

    }
    private  String returnUnitNumberName(int number){
        switch (number){
            case  1:
                return " one";
            case  2:
                return  " two";
            case  3:
                return  " three";
            case  4:
                return  " four";
            case  5:
                return  " five";
            case  6:
                return  " six";
            case  7:
                return  " seven";
            case  8:
                return  " eight";
            case  9:
                return  " nine";
            default:
                return  "";
        }
    }

    private  String returntenNumberName(int number){
        switch (number){
            case  10:
                return " ten";
            case  11:
                return " eleven";
            case  12:
                return  " twelve";
            case  13:
                return  " thirteen";
            case  14:
                return  " fourteen";
            case  15:
                return  " fifteen";
            case  16:
                return  " sixteen";
            case  17:
                return  " seventeen";
            case  18:
                return  " eighteen";
            case  19:
                return  " nineteen";
            default:
                return  "and";
        }
    }
    private  String returntenTohundredNumberName(int number){
        switch (number){
            case  2:
                return " twenty";
            case  3:
                return " thirty";
            case  4:
                return  " forty";
            case  5:
                return  " fifty";
            case  6:
                return  " sixty";
            case  7:
                return  " seventy";
            case  8:
                return  " eighty";
            case  9:
                return  " ninety";
            default:
                return  "";
        }
    }






}
