// Title:   Base60Converter.java
// Author:  Jacob Bello
// Course:  CST 336
// Date:    9/18/2024
// Abstract: This program takes numbers in base 60 and converts them into base 10

public class Base60Converter {

    public static double fromBase60(String base60String){
        double base10 = 0;


        // split string into array
        String[] nums = base60String.split(";");
        String integer = nums[0];


        String fraction;
        if (nums.length > 1){
            fraction = nums[1];
        } else {
            fraction = "";
        }


        //convert
        for (int i = 0; i < integer.length(); i++){
            char digit = integer.charAt(i);
            int digitVal = Character.getNumericValue(digit);
            base10 += digitVal * Math.pow(60, integer.length() - i - 1);
        }

//        convert fraction
        for (int i = 0; i < fraction.length(); i++) {
            char digit = fraction.charAt(i);
            int digitVal = Character.getNumericValue(digit);
            base10 += digitVal * Math.pow(60, -(i+1));

        }

        return base10;
    }

    public static void main(String[] args) {
        String base60String = "1;22,7,42,33,4,40";
        double result = fromBase60(base60String);
        System.out.println("Base 10 value: " + result);
    }
}
