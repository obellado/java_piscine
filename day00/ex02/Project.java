import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int number = 0;
        while (true) {
            number = scanner.nextInt();
            if (number == 42) {
                count++;
                break;
            }
            int length = String.valueOf(number).length();
            if (intIsPrime(length))
                count++;
        }
        System.out.println("Count of coffee-request - "+count);
    }

    public static boolean intIsPrime(int number) {
        System.out.println("len = "+number);
        if (number <= 1) {
            return (false);
        }
        for (int i = 2; (i * i) <= number; ) {
            if (number % i == 0) {
                return (false);
            }
            i++;
        }
        return (true);
    }
}