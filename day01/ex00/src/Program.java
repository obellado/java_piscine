public class Program {
    public static void main(String[] args) {
        try {
            User A = new User("Anna", 2000);
            User B = new User("Maria", 8);
            Transaction one = new Transaction(A, B, TransferCategory.DEBITS, 100);
            Transaction two = new Transaction(A, B, TransferCategory.CREDITS, 100000);
            System.out.println(A);
            System.out.println(B);
            System.out.println(one);
            System.out.println(two);
        } catch (ExceptionInInitializerError e) {
            System.out.println("Oops!");
        }
    }
}
