import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final String signatureAddress = "/Users/obellado/Desktop/Java_In_Progress/day02/ex00/signatures.txt";
    private static final String signatureAddress2 = "../signatures.txt";

    public static void main(String[] args) {

        File signatureFile = new File(signatureAddress);
        Map<short[], String> data = SignatureParser.signatureSourceFileParser(signatureFile);

//        while (scanner.hasNextLine()) {
//            String hex = SignatureParser.signatureParser(new scanner.nextLine());

            //String result = SignatureParser.signatureCompare(hex, data);
       // }


        //Map<int[], String> signData =

    }
}
