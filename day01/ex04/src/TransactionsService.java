import java.util.UUID;

public class TransactionsService {
    UsersList users;

    TransactionsService() {
        users = new UsersArrayList();
    }
    public void addUser(User user) {
        users.addUser(user);
    }
    public Integer retrieveUsersBalance(Integer userId) {
        return users.retrieveByID(userId).getBalance();
    }
    public void performTransaction(Integer senderId, Integer recipientId, Integer amount) {
        User sender = users.retrieveByID(senderId);
        if (sender.getBalance() > amount) {
            User recipient = users.retrieveByID(recipientId);
            try {
                sender.getTransactions().add(new Transaction(sender, recipient, TransferCategory.CREDITS, amount));
                recipient.getTransactions().add(new Transaction(sender, recipient, TransferCategory.DEBITS, amount));
                int sum = recipient.getBalance() + amount;
                recipient.setBalance(sum);
                sum = sender.getBalance() - amount;
                sender.setBalance(sum);
            } catch (Throwable e) {
                System.out.println("Sorry.. Couldn't make a transfer");
            }

        }
        else {
            throw new IllegalTransactionException();
        }
    }
    public Transaction[] retrieveTransfers(Integer userId) {
        return users.retrieveByID(userId).getTransactions().toArray();
    }
    public void removeTransfer(UUID transactionId, Integer userId) {
        users.retrieveByID(userId).getTransactions().remove(transactionId);
    }
    public Transaction[] checkValidity(){
        return new Transaction[0];
    }

    public class IllegalTransactionException extends RuntimeException {

    }

}
