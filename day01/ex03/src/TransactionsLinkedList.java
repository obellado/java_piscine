import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction transaction;
    private TransactionsLinkedList next = null;
    private TransactionsLinkedList previous = null;


    TransactionsLinkedList() {
        this.transaction = null;
        this.next = null;
        this.previous = null;
    }
    TransactionsLinkedList(Transaction t) {
        this.transaction = t;
        this.next = null;
        this.previous = null;
    }

    @Override
    public void add(Transaction t) {
        TransactionsLinkedList node = new TransactionsLinkedList(t);
        node.next = this.next;
        this.next = node;
        node.previous = this;

    }

    @Override
    public void remove(UUID id) {

    }

    @Override
    public Transaction[] toArray() {
        TransactionsLinkedList tmp = this;
        int n = 0;
        if (tmp != null)
            n = 1;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        while (tmp.previous != null) {
            n++;
            tmp = tmp.previous;
        }
        Transaction[] arr = new Transaction[n];
        for (int i = 0; i < n; i++) {
            arr[i] = tmp.transaction;
            tmp = tmp.next;
        }
        return arr;
    }

}
