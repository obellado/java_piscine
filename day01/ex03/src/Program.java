import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        try {
            User B = new User("Maria", 100);
            User B1 = new User("Maria", 200);
            User B2 = new User("Maria", 300);
            User B3 = new User("Maria", 100);
            User B4 = new User("Maria", 100);
            User B5 = new User("Maria", 100);
            User B6 = new User("Maria", 100);
            User B7 = new User("Maria", 100);
            User B8 = new User("Maria", 100);
            User B9 = new User("Maria", 100);
            User B10 = new User("Anna", 100);
            User B11 = new User("Anna", 100);
            UsersArrayList A = new UsersArrayList();
            A.addUser(B);
            A.addUser(B1);
            A.addUser(B2);
            A.addUser(B3);
            A.addUser(B4);
            A.addUser(B5);
            A.addUser(B6);
            A.addUser(B7);
            A.addUser(B8);
            A.addUser(B9);
            A.addUser(B10);
            A.addUser(B11);

            Transaction T1 = new Transaction(B1, B, TransferCategory.DEBITS, 100);
            Transaction T2 = new Transaction(B2, B, TransferCategory.CREDITS, -100);
            Transaction T3 = new Transaction(B3, B, TransferCategory.DEBITS, 100);
            Transaction T4 = new Transaction(B4, B, TransferCategory.CREDITS, -100);
            Transaction T5 = new Transaction(B5, B, TransferCategory.CREDITS, -100);

            TransactionsLinkedList T = new TransactionsLinkedList();

            T.add(T1);
            T.add(T2);
            T.add(T3);
            T.add(T4);

            T.remove(T4.getIdentifier());

            Transaction[] arr = T.toArray();

            System.out.println("Array is made:");
            System.out.println(arr[0]);
            System.out.println(arr[1]);
            System.out.println(arr[2]);

            System.out.println("Array size: " + arr.length);
            T.remove(UUID.randomUUID());
        } catch (TransactionNotFoundException e) {
            System.out.print("Oops! ");
            e.printStackTrace();
        }
    }
}
