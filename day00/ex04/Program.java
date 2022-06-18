import java.util.Scanner;

public class Program {
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

        while (array.length > 0){
            ch = array[0];
            count = 0;
            for (int i = 0; i < array.length; ) {
                if (ch == array[i]) {
                    count++;
                    array = remove(array, i);
                }
                else
                    i++;
            }
            fillTable(chars, counts, ch, count);
        }
        paintTable(chars,counts);
    }
    public static void paintTable(char[] chars, int[] counts){
        int d = counts[0];
        int[] flags = new int[10];
        for (int i = 11; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                if (counts[j] * 11 / (d * i) >= 1)
                    if (flags[j] == 0) {
                        System.out.print("   " + counts[j]);
                        flags[j] = 1;
                    } else
                        System.out.print("   #");
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++) {
            System.out.print("   " + chars[i]);
        }
        System.out.println();
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
    public static char[] remove(char[] arr, int index){
        int len = arr.length - 1;
        char[] array = new char[len];
        for (int i = 0; i < index; i++){
            array[i] = arr[i];
        }
        for (int i = index; i < len; i++){
            array[i] = arr[i + 1];
        }
        return array;
    }
}