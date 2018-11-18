/*
RMIT University Vietnam
Course: INTE2512 Object-Oriented Programming
Semester: 2018C
Assessment: Assignment 1
Author: Pham Nguyen Thanh Nhan
ID: S3563953
Created date: 15/11/2018
Acknowledgement:
https://stackoverflow.com/questions/19108902/java-how-to-ask-user-if-he-she-wants-to-continue-program
http://grails.asia/two-dimensional-string-array-in-java
https://stackoverflow.com/questions/2390063/what-does-public-static-void-mean-in-java
https://softwareengineering.stackexchange.com/questions/234412/why-have-private-static-methods
*/
import java.util.Scanner;
public class PhoneNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the phone number: ");
        String phoneNumber = input.next();
        int result = Integer.valueOf(phoneNumber);
        if (result < 0) {
            System.out.println("Program exits. Goodbye!!!");
        }else {
            conditionOfPhoneNumber(phoneNumber);
        }
    }
    // check phone is follow the format XXXYYYYYYY or XXXXYYYYYYY in which XXX or XXXX is the Network Code.
    private static void conditionOfPhoneNumber (String phone) {
        String[] tenDigits = {"086", "096", "097", "098", "089", "090", "093", "088", "091", "094", "092", "099"};
        String[][] elevenDigits = {{"0162", "032"}, {"0163", "033"}, {"0164", "034"}, {"0165", "035"}, {"0166", "036"}, {"0167", "037"},
                {"0168", "038"}, {"0169", "039"}, {"0120", "070"}, {"0122", "077"}, {"0121", "079"}, {"0126", "076"}, {"0128", "078"},
                {"0123", "083"}, {"0124", "084"}, {"0125", "085"}, {"0127", "081"}, {"0129", "082"}, {"0186", "056"}, {"0188", "058"}, {"0199", "059"}};
        String regex1 = "(\\d{4})(\\d{7})";
        String regex = "(\\d{3})(\\d{7})";
        // using the substring to take the 3 first number in input
        String s1 = phone.substring(0, 3);
        String s2 = phone.substring(0, 4);
        // checking the phone in the network
        boolean found = checkIfExists(tenDigits, s1);
        boolean found1 = checkIfExists1(elevenDigits,s2);
        // Condition of input
        if (phone.matches(regex) && found) {
            System.out.println("No conversion needed as this number has 10 digits.");
        } else if (phone.matches(regex1)&& found1) {
            String covertElevenPhoneNumber = covertphone(elevenDigits, phone);// using the function to convert the phone have 11 digits to 10 digits
            System.out.println("The new number is " + covertElevenPhoneNumber);
        } else {
            System.out.println("Invalid phone number");
        }
    }
    // check  phone have 10 digits, which have in network
    private static boolean checkIfExists(String[] digit, String phone) {
        for (String element : digit) {
            if (element.equals(phone)) {
                return true;
            }
        }return false;
    }
    // check  phone have 11 digits, which have in network
    private static boolean checkIfExists1(String[][] digit, String phone) {
        String s2 = phone.substring(0, 4);
        for (int i = 0; i <digit.length ; i++) {
            if (s2.equals(digit[i][0])) {
                return true;
            }
        }return false;
    }
    // create function to convert the phone have 11 digits to 10 digits
    private static String covertphone(String [][] digit, String elevenNumberInPhone){
        String finalphone = "";
        String s2 = elevenNumberInPhone.substring(0, 4);
        for (int i = 0; i <digit.length ; i++) {
            if (s2.equals(digit[i][0])) {
                finalphone = digit[i][1] + elevenNumberInPhone.substring(4,11);
            }
        }return finalphone;
    }
}

