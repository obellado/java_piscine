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
            System.out.println(A.size());
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
            System.out.println(A.size());
            Transaction T1 = new Transaction(B1, B, TransferCategory.DEBITS, 100);
            Transaction T2 = new Transaction(B2, B, TransferCategory.CREDITS, 100000);
            Transaction T3 = new Transaction(B3, B, TransferCategory.DEBITS, 100);
            Transaction T4 = new Transaction(B4, B, TransferCategory.CREDITS, 100000);
            TransactionsLinkedList T = new TransactionsLinkedList();
            T.add(T1);
            T.add(T2);
            T.add(T3);
            T.add(T4);
            Transaction[] arr = T.toArray();

            System.out.println(arr[0]);
            System.out.println(arr[1]);
            System.out.println(arr[2]);
            System.out.println(arr[3]);
            //System.out.println("Retrieved Number : " + A.retrieveNumber());
            //System.out.println("Retrieved by ID(3) : " + A.retrieveByID(3));
            //System.out.println("Retrieved by index(2) : " + A.retrieveByIndex(2));
            //System.out.println("Retrieved by ID (out of boundaries) : " + A.retrieveByID(19));
            //System.out.println(one);
            //System.out.println(two);


        } catch (Throwable e) {
            System.out.println("Oops!");
        }
    }
}
