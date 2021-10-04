import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileSignatureParser {
    public static List<String> fileFlow(Map<int[], String> data){
        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String newLine  = scanner.nextLine();
            if (newLine.equals("42")) {
                break ;
            }
            File signatureFile = new File(newLine);
            int[] hex = signatureParser(signatureFile);
            String res = signatureCompare(hex, data);
            if (res != null)
                result.add(res);
        }
        return result;
    }
    public static int[] signatureParser(File signatureFile) {
        try (InputStream input = new FileInputStream(signatureFile)) {
            int symbol = 0;
            int n = 0;
            int[] hex = new int[16];
            while ((symbol = input.read()) != -1 && n < 16 ) {
                hex[n] = symbol;
                n++;
            }
            return hex;
        } catch (FileNotFoundException e) {
            System.out.println("Oops!");
        } catch (IOException e) {
            System.out.println("Oops!");
        }
        return null;
    }
    public static String signatureCompare(int[] hex, Map<int[], String> data){
        boolean result = true;
        for (int[] key : data.keySet()) {
            for (int i = 0; i < key.length; i++) {
                if (key[i] != hex[i]) {
                    break ;
                }
                if (i == key.length - 1) {
                    return data.get(key);
                }
            }
        }
        return null;

    }
}
