import java.util.UUID;

public interface TransactionsList {
    void add(Transaction t);
    void remove(UUID id);
    Transaction[] toArray();
}
