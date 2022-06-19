import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Program {

    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception();
        Set<String> dictionary = new HashSet<>();
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        FileReader reader1 = new FileReader(args[0]);
        BufferedReader bufferedReader1 = new BufferedReader(reader1);
        String str1 = bufferedReader1.readLine();
        while ( str1 != null) {
            addToDictionary(str1, dictionary, map1, map2);
            str1 = bufferedReader1.readLine();
        }
        FileReader reader2 = new FileReader(args[1]);
        BufferedReader bufferedReader2 = new BufferedReader(reader2);
        String str2 = bufferedReader2.readLine();
        while ( str2 != null) {
            addToDictionary(str2, dictionary, map2, map1);
            str2 = bufferedReader2.readLine();
        }
        reader1.close();
        bufferedReader1.close();
        reader2.close();
        bufferedReader2.close();
        try (FileOutputStream output = new FileOutputStream("dictionary.txt")) {
            for (String line : dictionary) {
                output.write((line + ' ').getBytes(StandardCharsets.UTF_8));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File result.txt not found");
        } catch (IOException e) {
            System.out.println("Oops!");
        }
        System.out.println("Similarity = " + similarity(map1.values().stream().toArray(), map2.values().stream().toArray()));
    }
    private static void addToDictionary(String str, Set<String> dictionary, Map<String, Integer> map1, Map<String, Integer> map2) {
        String[] arr = str.split(" ");
        for (int i = 0; i < arr.length; i++) {
            dictionary.add(arr[i]);
            if (map1.containsKey(arr[i]))
                map1.put(arr[i], map1.get(arr[i]) + 1);
            else
                map1.put(arr[i], 1);
            if (!map2.containsKey(arr[i]))
                map2.put(arr[i], 0);
        }
    }
    private static Double similarity(Object[] vector1, Object[] vector2) {
        Double numerator = 0.0;
        Double denominator1 = 0.0;
        Double denominator2 = 0.0;
        for (int i = 0; i < vector1.length; i++) {
            numerator += (Integer)vector1[i] * (Integer)vector2[i];
            denominator1 += (Integer)vector1[i] * (Integer)vector1[i];
            denominator2 += (Integer)vector2[i] * (Integer)vector2[i];
        }
        return numerator/(Math.sqrt(denominator1) * Math.sqrt(denominator2));
    }
}
