/***
 * Week-4 Assignment - 1
 * 
 * This program contains 2 files i.e. main file and constants file.
 * This Program converts Decimal Number, Binary Number, octal Number, Hexadecimal Number into each other and perform arithematic operation among them.
 * 
 * Program Owner - Keshav Kumar
 * Date - 24/09/2024
 */
import java.util.Scanner;

public class Conversions {
    public static Scanner input = new Scanner(System.in);
    static Constants constants = new Constants();

    /***
     * This method converts the input Decimal Number into the Binary Number.
     * Parameter - Input Decimal number.
     * Return - Binary Number
     */

    public static String decimalToBinary(double decimal) {
        String binary = constants.EMPTY_STRING;
        int integerPart = (int) decimal;
        double fractionalPart = decimal - integerPart;
        if (integerPart == 0) {
            binary = constants.ZERO;
        } else {
            while (integerPart > 0) {
                binary = (integerPart % 2) + binary;
                integerPart /= 2;
            }
        }
        if (fractionalPart > 0) {
            binary += constants.DOT;
            while (fractionalPart > 0) {
                fractionalPart *= 2;
                int bit = (int) fractionalPart;
                binary += bit;
                fractionalPart -= bit;
            }
        }
        return binary;
    }

    /***
     * This method converts input Decimal Number to Octal Number.
     * Parameter - input Decimal Number
     * Return - Octal Number
     */

    public static String decimalToOctal(double decimal) {
        String octal = constants.EMPTY_STRING;
        int integerPart = (int) decimal;
        double fractionalPart = decimal - integerPart;
        if (integerPart == 0) {
            octal = constants.ZERO;
        } else {
            while (integerPart > 0) {
                octal = (integerPart % 8) + octal;
                integerPart /= 8;
            }
        }
        if (fractionalPart > 0) {
            octal += constants.DOT;
            while (fractionalPart > 0) {
                fractionalPart *= 8;
                int digit = (int) fractionalPart;
                octal += digit;
                fractionalPart -= digit;
            }
        }
        return octal;
    }

    /***
     * This method converts Input Decimal Number to Hexadecimal Number.
     * Parameter - input Decimal Number
     * Return - Hexadecimal Number
     */

    public static String decimalToHexadecimal(double decimal) {
        String hexadecimal = constants.EMPTY_STRING;
        int integerPart = (int) decimal;
        double fractionalPart = decimal - integerPart;
        if (integerPart == 0) {
            hexadecimal = constants.ZERO;
        } else {
            while (integerPart > 0) {
                int digit = integerPart % 16;
                if (digit < 10) {
                    hexadecimal = digit + hexadecimal;
                } else {
                    hexadecimal = (char) ('A' + (digit - 10)) + hexadecimal;
                }
                integerPart /= 16;
            }
        }
        if (fractionalPart > 0) {
            hexadecimal += constants.DOT;
            while (fractionalPart > 0) {
                fractionalPart *= 16;
                int digit = (int) fractionalPart;
                if (digit < 10) {
                    hexadecimal += digit;
                } else {
                    hexadecimal += (char) ('A' + (digit - 10));
                }
                fractionalPart -= digit;
            }
        }
        return hexadecimal;
    }

    /***
     * This method is used to verify if the given input String is Binary Number or not.
     * Parameter - input String 
     * Return - true or false
     */

    public static boolean isBinary(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (character != '0' && character != '1' && character != '.') {
                return false;
            }
        }
        return true;
    }

    /***
     * This method is used to verify if the given input String is Ocatal Number or not.
     * Parameter - input String
     * Return - true or false
     */

    public static boolean isOctal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches(constants.IS_OCTAL);
    }

    /***
     * This method is used to verify if the given input String is Hexadecimal Number or not.
     * Parameter - input String
     * Return - true or false
     */

    public static boolean isHexadecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (!((character >= '0' && character <= '9') || (character >= 'A' && character <= 'F') || (character >= 'a' && character <= 'f') || character == '.')) {
                return false;
            }
        }
        return true;
    }

    /***
     * This method converts the input binary String to decimal number.
     * Parameter - input binary number
     * Return - Decimal Number
     */

    public static double binaryToDecimal(String binary) {
        double decimal = 0.0;
        int pointIndex = findPointIndex(binary);
        for (int i = pointIndex - 1; i >= 0; i--) {
            char bit = binary.charAt(i);
            if (bit == '1') {
                decimal += power(2, pointIndex - 1 - i);
            }
        }
        for (int i = pointIndex + 1; i < binary.length(); i++) {
            char bit = binary.charAt(i);
            if (bit == '1') {
                decimal += 1.0 / power(2, i - pointIndex);
            }
        }
        return decimal;
    }

    /***
     * This method converts input octal number to decimal number .
     * Parameter - input Octal Number
     * Return - Decimal
     */

    public static double octalToDecimal(String octal) {
        double decimal = 0.0;
        int pointIndex = findPointIndex(octal);
        for (int i = pointIndex - 1; i >= 0; i--) {
            char digit = octal.charAt(i);
            decimal += (digit - '0') * power(8, pointIndex - 1 - i);
        }
        for (int i = pointIndex + 1; i < octal.length(); i++) {
            char digit = octal.charAt(i);
            decimal += (digit - '0') * (1.0 / power(8, i - pointIndex));
        }
        return decimal;
    }

    /***
     * This method converts input Hexadecimal Number to Decimal Number.
     * Parameter - input Hexadecimal Number
     * Return - Decimal Number
     */

    public static double hexadecimalToDecimal(String hexadecimal) {
        double result = 0.0;
        String[] number = hexadecimal.split(constants.DECIMAL_SPLIT);
        for (int i = 0; i < number[0].length(); i++) {
            char character = number[0].charAt(number[0].length() - 1 - i);
            int digitValue = hexCharToDecimal(character);
            result += digitValue * power(16, i);
        }
        if (number.length > 1) {
            for (int i = 0; i < number[1].length(); i++) {
                char character = number[1].charAt(i);
                int digitValue = hexCharToDecimal(character);
                result += digitValue * power(16, -(i + 1));
            }
        }
        return result;
    }

    /***
     * This method converts number greater than 9 into A,B,C... alphabets.
     * Parameter - hexadecimal Number
     * Return - alphabet
     */

    public static int hexCharToDecimal(char hexNumber) {
        if (hexNumber >= '0' && hexNumber <= '9') {
            return hexNumber - '0';
        } else if (hexNumber >= 'A' && hexNumber <= 'F') {
            return hexNumber - 'A' + 10;
        } else if (hexNumber >= 'a' && hexNumber <= 'f') {
            return hexNumber - 'a' + 10;
        } else {
            throw new NumberFormatException(constants.INVALID_HEXA_CHAR);
        }
    }

    /***
     * This method finds the length of the decimal number.
     * Parameter - number
     * Return - number
     */

    //Method to find the index of the decimal point
    public static int findPointIndex(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == '.') {
                return i;
            }
        }
        return number.length();
    }

    /***
     * This method is the substitute for Math.power
     * Parameter - base and exponent
     * Return - result
     */

    //Calculate power of a number 
    public static double power(int base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    /***
     * This method converts any base number to decimal number.
     * Parameter - input Number and base
     * Return - decimal Number.
     */

    public static int baseToDecimal(String number, int base) {
        int decimalValue = 0;
        int length = number.length();

        for (int i = 0; i < length; i++) {
            char character = number.charAt(i);
            int digitValue;
            if (Character.isDigit(character)) {
                digitValue = character - '0';
            } else {
                digitValue = Character.toUpperCase(character) - 'A' + 10;
            }
            if (digitValue >= base) {
                throw new IllegalArgumentException(constants.INVALID_CHAR + character + constants.FOR_BASE + base);
            }
            int power = 1;
            for (int j = 0; j < length - i - 1; j++) {
                power *= base;
            }
            decimalValue += digitValue * power;
        }
        return decimalValue;
    }

    /***
     * This method converts decimal number to any base number.
     * Parameter - decimal number, base
     * Return - number
     */

    public static String decimalToBase(int decimal, int base) {
        if (decimal == 0) {
            return constants.ZERO;
        }
        String result = constants.EMPTY_STRING;
        while (decimal > 0) {
            int remainder = decimal % base;
            char digit;
            if (remainder < 10) {
                digit = (char) ('0' + remainder);
            } else {
                digit = (char) ('A' + (remainder - 10));
            }
            result = digit + result;
            decimal /= base;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(constants.WELCOME);
        System.out.println(constants.OPTIONS);
        System.out.print(constants.CHOICE);
        String choice = input.next();
        boolean quit = true;
        do{
            switch (choice) {
                case "1":
                    try{
                        System.out.print(constants.ENTER_NUMBER);
                        double inputNumber = input.nextDouble();
                        if(inputNumber < 0){
                            System.out.println(constants.NUM_GREATER_ZERO);
                        }
                        System.out.println(constants.BINARY + decimalToBinary(inputNumber));
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;
                
                case "2":
                    try{
                        System.out.print(constants.ENTER_NUMBER);
                        double inputNumber = input.nextDouble();
                        if(inputNumber < 0){
                            System.out.println(constants.NUM_GREATER_ZERO);
                        }
                        System.out.println(constants.OCTAL + decimalToOctal(inputNumber));
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "3":
                    try{
                        System.out.print(constants.ENTER_NUMBER);
                        double inputNumber = input.nextDouble();
                        if(inputNumber < 0){
                            System.out.println(constants.NUM_GREATER_ZERO);
                        }
                        System.out.println(constants.HEXA + decimalToHexadecimal(inputNumber));
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "4":
                    try{
                        System.out.print(constants.BINARY_INPUT);
                        String inputNumber = input.next();
                        if(isBinary(inputNumber)){
                            System.out.println(constants.DECIMAL + binaryToDecimal(inputNumber));
                        }else{
                            System.out.println(constants.INVALID_BINARY);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "5":
                    try{
                        System.out.print(constants.BINARY_INPUT);
                        String inputNumber = input.next();
                        if(isBinary(inputNumber)){
                            Double decimal = binaryToDecimal(inputNumber);
                            String octal = decimalToOctal(decimal);
                            System.out.println(constants.OCTAL + octal);
                        }else{
                            System.out.println(constants.INVALID_BINARY);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "6":
                    try{
                        System.out.print(constants.BINARY_INPUT);
                        String inputNumber = input.next();
                        if(isBinary(inputNumber)){
                            Double decimal = binaryToDecimal(inputNumber);
                            String hexaDecimal = decimalToHexadecimal(decimal);
                            System.out.println(constants.HEXA + hexaDecimal);
                        }else{
                            System.out.println(constants.INVALID_BINARY);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "7":
                    try{
                        System.out.print(constants.OCTAL_INPUT);
                        String inputNumber = input.next();
                        if (isOctal(inputNumber)) {
                            System.out.println(constants.DECIMAL + octalToDecimal(inputNumber));
                        } else {
                            System.out.println(constants.INVALID_OCTAL);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "8":
                    try{
                        System.out.print(constants.OCTAL_INPUT);
                        String inputNumber = input.next();
                        if (isOctal(inputNumber)) {
                            Double decimal = octalToDecimal(inputNumber);
                            String binary = decimalToBinary(decimal);
                            System.out.println(constants.BINARY + binary);
                        } else {
                            System.out.println(constants.INVALID_OCTAL);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "9":
                    try{
                        System.out.print(constants.OCTAL_INPUT);
                        String inputNumber = input.next();
                        if (isOctal(inputNumber)) {
                            Double decimal = octalToDecimal(inputNumber);
                            String hexaDecimal = decimalToHexadecimal(decimal);
                            System.out.println(constants.HEXA + hexaDecimal);
                        } else {
                            System.out.println(constants.INVALID_OCTAL);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "10":
                    try{
                        System.out.print(constants.HEXA_INPUT);
                        String inputNumber = input.next();
                        if (isHexadecimal(inputNumber)) {
                            System.out.println(constants.DECIMAL + hexadecimalToDecimal(inputNumber));
                        } else {
                            System.out.println(constants.INVALID_HEXA);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "11":
                    try{
                        System.out.print(constants.HEXA_INPUT);
                        String inputNumber = input.next();
                        if (isHexadecimal(inputNumber)) {
                            Double decimal = hexadecimalToDecimal(inputNumber);
                            String binary = decimalToBinary(decimal);
                            System.out.println(binary);
                        } else {
                            System.out.println(constants.INVALID_HEXA);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "12":
                    try{
                        System.out.print(constants.HEXA_INPUT);
                        String inputNumber = input.next();
                        if (isHexadecimal(inputNumber)) {
                            Double decimal = hexadecimalToDecimal(inputNumber);
                            String octal = decimalToOctal(decimal);
                            System.out.println(constants.OCTAL + octal);
                        } else {
                            System.out.println(constants.INVALID_HEXA);
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "13":
                    try{
                        System.out.print(constants.FIRST_NUMBER);
                        String inputNumber1 = input.next();
                        System.out.print(constants.BASE_1);
                        int base = input.nextInt();
                        int firstNumber = baseToDecimal(inputNumber1, base);
                        System.out.print(constants.SECOND_NUMBER);
                        String inputNumber2 = input.next();
                        System.out.print(constants.BASE_2);
                        int base2 = input.nextInt();
                        int secondNumber = baseToDecimal(inputNumber2, base2);
                        int result = firstNumber + secondNumber ;
                        System.out.print(constants.BASE_3);
                        int base3 = input.nextInt();
                        if(base < 2 || base > 16 || base2 < 2 || base2 > 16 || base3 < 2 || base3 > 16){
                            System.out.println(constants.INVALID_BASE);
                        }else{
                            System.out.println(constants.OUTPUT + decimalToBase(result, base3));
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "14":
                    try{
                        System.out.println(constants.FIRST_NUMBER);
                        String inputNumber1 = input.next();
                        System.out.println(constants.BASE_1);
                        int base = input.nextInt();
                        int firstNumber = baseToDecimal(inputNumber1, base);
                        System.out.println(constants.SECOND_NUMBER);
                        String inputNumber2 = input.next();
                        System.out.println(constants.BASE_2);
                        int base2 = input.nextInt();
                        int secondNumber = baseToDecimal(inputNumber2, base2);
                        int result = firstNumber - secondNumber ;
                        System.out.println(constants.BASE_3);
                        int base3 = input.nextInt();
                        if(base < 2 || base > 16 || base2 < 2 || base2 > 16 || base3 < 2 || base3 > 16){
                            System.out.println(constants.INVALID_BASE);
                        }else{
                            System.out.println(constants.OUTPUT + decimalToBase(result, base3));
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "15":
                    try{
                        System.out.println(constants.FIRST_NUMBER);
                        String inputNumber1 = input.next();
                        System.out.println(constants.BASE_1);
                        int base = input.nextInt();
                        int firstNumber = baseToDecimal(inputNumber1, base);
                        System.out.println(constants.SECOND_NUMBER);
                        String inputNumber2 = input.next();
                        System.out.println(constants.BASE_2);
                        int base2 = input.nextInt();
                        int secondNumber = baseToDecimal(inputNumber2, base2);
                        int result = firstNumber * secondNumber ;
                        System.out.println(constants.BASE_3);
                        int base3 = input.nextInt();
                        if(base < 2 || base > 16 || base2 < 2 || base2 > 16 || base3 < 2 || base3 > 16){
                            System.out.println(constants.INVALID_BASE);
                        }else{
                            System.out.println(constants.OUTPUT + decimalToBase(result, base3));
                        }
                    }catch(Exception e){
                        System.err.println(constants.INVALID_INPUT);
                    }
                    break;

                case "16":
                    try{
                        System.out.println(constants.FIRST_NUMBER);
                        String inputNumber1 = input.next();
                        System.out.println(constants.BASE_1);
                        int base = input.nextInt();
                        int firstNumber = baseToDecimal(inputNumber1, base);
                        System.out.println(constants.SECOND_NUMBER);
                        String inputNumber2 = input.next();
                        System.out.println(constants.BASE_2);
                        int base2 = input.nextInt();
                        int secondNumber = baseToDecimal(inputNumber2, base2);
                        int result = firstNumber / secondNumber ;
                        System.out.println(constants.BASE_3);
                        int base3 = input.nextInt();
                        if(base < 2 || base > 16 || base2 < 2 || base2 > 16 || base3 < 2 || base3 > 16){
                            System.out.println(constants.INVALID_BASE);
                        }else{
                            System.out.println(constants.OUTPUT + decimalToBase(result, base3));
                        }
                    }catch(Exception e){
                        System.out.println(constants.INVALID_INPUT);
                    }
                    break;

                case "0" :
                    System.out.println(constants.EXIT);
                    quit = false;
                    break;

                default:
                    System.out.println(constants.INVALID);
                    break;
            }if(quit){
                System.out.print(constants.CHOICE);
                input.nextLine();
                choice = input.next();
            }
        }while(quit);
        input.close();
    }
}
