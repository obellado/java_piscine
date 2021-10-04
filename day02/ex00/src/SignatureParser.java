import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SignatureParser {
    public static int countingKey(String line) {
        int n = 0;
        Scanner scan = new Scanner(line);
        if (scan.hasNext()) {
            scan.next();
        }
        while (scan.hasNextInt(16)) {
            scan.nextInt(16);
            n++;
        }
        return n;
    }

    public static int[] parsingKey(String line){
        int n = countingKey(line);
        Scanner scan = new Scanner(line);
        if (scan.hasNext()) {
            scan.next();
        }
        int[] key = new int[n];
        n = 0;
        while (scan.hasNextInt(16)) {
            key[n] = scan.nextInt(16);
            n++;
        }
        return key;
    }
    public static String parsingValue(String line){
        StringBuilder value = new StringBuilder();
        for (int i = 0; line.charAt(i) != ','; i++)
            value.append(line.charAt(i));
        return value.toString();
    }
    public static Map<int[], String> signatureSourceFileParser(File signatureFile){
        try (Scanner scanner = new Scanner(signatureFile)) {
            Map<int[], String> base = new HashMap<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int[] key = parsingKey(line);
                String value = parsingValue(line);
                base.put(key, value);
            }
            return base;
        } catch (FileNotFoundException e) {
            System.out.println("Oops!");
        }
        return null;
    }
}
