import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final String signatureAddress = "/Users/obellado/Desktop/java_piscine/day02/ex00/signatures.txt";
    private static final String signatureAddress2 = "../signatures.txt";

    public static void main(String[] args) {

        File signatureFile = new File(signatureAddress);
        Map<int[], String> data = SignatureParser.signatureSourceFileParser(signatureFile);

        List<String> result = FileSignatureParser.fileFlow(data);
        try (FileOutputStream output = new FileOutputStream("result.txt")) {
            for (String line : result) {
                output.write((line + '\n').getBytes(StandardCharsets.UTF_8));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File result.txt not found");
        } catch (IOException e) {
            System.out.println("Oops!");
        }
    }
}
