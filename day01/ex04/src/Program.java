public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();
        User M = new User("Maria", 100);
        User A = new User("Anna", 2000);
        try {
            service.addUser(A);
            service.addUser(M);
            service.performTransaction(A.getIdentifier(), M.getIdentifier(), 800);
            service.performTransaction(M.getIdentifier(), A.getIdentifier(), 80);
            service.performTransaction(A.getIdentifier(), M.getIdentifier(), 8);
//            System.out.println(service.retrieveTransfers(A.getIdentifier())[0]);
//            System.out.println(service.retrieveTransfers(M.getIdentifier())[0]);
//            System.out.println(service.retrieveTransfers(A.getIdentifier())[2]);
//            System.out.println(service.retrieveTransfers(M.getIdentifier())[2]);
        } catch (TransactionsService.IllegalTransactionException e) {
            System.out.println("Couldnt make a transfer");
        } catch (TransactionsLinkedList.TransactionNotFoundException e) {
            System.out.println("Couldnt find a transfer");
        } catch (UsersArrayList.UserNotFoundException e) {
            System.out.println("Couldnt find the User");
        }
        System.out.println(A);
        System.out.println(M);
    }
}
