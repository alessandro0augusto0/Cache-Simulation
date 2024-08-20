package utils;

public class Convert {
    public static String intToBinaryString(Long value, int size) {
        String binaryString = Long.toBinaryString(value);
        while (binaryString.length() < size) {
            binaryString = "0" + binaryString;
        }
        return binaryString;
    }
}
