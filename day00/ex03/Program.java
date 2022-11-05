import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfWeeks = 1;
        long grades = 0;
        int min = 9;
        int buf = 0;
        String line = scanner.nextLine();
        for (; numberOfWeeks <= 18; numberOfWeeks++){
            if (line.equals("42")) {
                break;
            }
            if (!line.equals("Week "+numberOfWeeks)) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
            min = 9;
            for (int j = 0; j < 5; j++){
                buf = scanner.nextInt();
                if (buf < min) {
                    min = buf;
                }
            }
            grades += min * (Math.pow(10, numberOfWeeks - 1));
            line = scanner.nextLine();
            line = scanner.nextLine();
            //System.out.println("grades = "+grades);
        }
        for (int i = 1; i < numberOfWeeks; i++){
            System.out.print("Week " + i +" ");
            long n = grades % 10;
            for (int j = 0; j < n; j++){
                System.out.print("=");
            }
            System.out.println(">");
            grades = grades / (long)10;
        }
    }
}