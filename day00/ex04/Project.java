import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        char[] chars = new char[10];
        int[] counts = new int[10];
        int len = line.length();
        char[] array = new char[len];
        array = line.toCharArray();
        int count = 0;
        char ch;
        for i
            fillTable(chars, counts, ch, count);
            //System.out.println(ch);
            //System.out.println(count);
        }
        paintTable(chars,counts);
    }
    public static void paintTable(char[] chars, int[] counts){
        int d = counts[0];
        System.out.println();
        System.out.println();

        for (int i = 10; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (counts[j] * 10 / (d * i) >= 1)
                    System.out.print("#    ");
            }
            System.out.println();
        }
        System.out.println(chars);
    }
    public static void fillTable(char[] chars, int[] counts, char c, int n) {
        int index = 10;
        for (int i = 9; i >= 0; i--) {
            if (counts[i] > n)
                break;
            else if (counts[i] == n){
                if (chars[i] < c)
                    index = i + 1;
                else
                    index = i;
            }
            else
                index = i;
        }
        if (index > 9)
            return ;
        else {
            for (int i = 9; i > index; i--){
                counts[i] = counts[i-1];
                chars[i] = chars[i-1];
            }
            counts[index] = n;
            chars[index] = c;
        }
    }
}