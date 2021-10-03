import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SignatureParser {
    public static String signatureParser(File signatureFile) {
        try (InputStream input = new FileInputStream(signatureFile)) {
            int symbol = 0;
            symbol = input.read();
            System.out.println(symbol);
            StringBuilder hex = new StringBuilder();
            hex.append(String.format("%X", symbol));
            System.out.println(hex);
            return hex.toString();
            //int value = 0;

        } catch (FileNotFoundException e) {
            System.out.println("Oops!");
        } catch (IOException e) {
            System.out.println("Oops!");
        }
        return null;
    }
    public static String signatureCompare(String hex, Map<byte[], String> data){
        return hex;

    }
    public static Map<short[], String> signatureSourceFileParser(File signatureFile){
        try (Scanner scanner = new Scanner(signatureFile)) {
            Map<short[], String> base = new HashMap<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                short[] key =  ;
                String value = 
                base.put(key, value);

            }
            return base;
        } catch (FileNotFoundException e) {
            System.out.println("Oops!");
        }
        return null;
    }
}
