public class Program {
    public static void main(String[] args) {
        try {
            User A = new User("Anna", 2000);
            User B = new User("Maria", 8);
            User B1 = new User("Maria", 8);
            User B2 = new User("Maria", 8);
            User B3 = new User("Maria", 8);
            User B4 = new User("Maria", 8);
            //Transaction one = new Transaction(A, B, TransferCategory.DEBITS, 100);
            //Transaction two = new Transaction(A, B, TransferCategory.CREDITS, 100000);
            System.out.println(A);
            System.out.println(B);
            System.out.println(B1);
            System.out.println(B2);
            System.out.println(B3);
            System.out.println(B4);
            //System.out.println(one);
            //System.out.println(two);
        } catch (ExceptionInInitializerError e) {
            System.out.println("Oops!");
        }
    }
}
