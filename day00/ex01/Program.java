import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long number = scanner.nextLong();
        if (number <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        int counter = 1;
        for (int i = 2; (i * i) <= number; ) {
            if (number % i == 0) {
                System.out.println("false "+ counter);
                return ;
            }
            counter++;
            i++;
        }
        System.out.println("true "+ counter);
    }
}