import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction transaction;
    private TransactionsLinkedList next;
    private TransactionsLinkedList previous;
    private int size;

    TransactionsLinkedList() {
        this.transaction = null;
        this.next = null;
        this.previous = null;
        this.size = 0;
    }
    TransactionsLinkedList(Transaction t) {
        this.transaction = t;
        this.next = null;
        this.previous = null;
    }

    @Override
    public void add(Transaction t) {
        this.size++;
        TransactionsLinkedList node = new TransactionsLinkedList(t);
        node.next = this.next;
        if (this.next != null)
            this.next.previous = node;
        this.next = node;
        node.previous = this;
    }

    @Override
    public void remove(UUID id) {
        String ID = id.toString();
        TransactionsLinkedList tmp = this.next;
        for (int i = 0; i < this.size ; i++) {
            if (tmp.transaction.getIdentifier().toString().equals(ID)) {
                TransactionsLinkedList p = tmp.previous;
                TransactionsLinkedList n = tmp.next;
                if (p != null) {
                    p.next = n;
                    tmp = n;
                }
                if (n != null) {
                    n.previous = p;
                }
                this.size = this.size - 1;
                return ;
            }
            tmp = tmp.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        TransactionsLinkedList tmp = this.next;
        Transaction[] arr = new Transaction[this.size];
        for (int i = 0; i < this.size ; i++) {
            arr[i] = tmp.transaction;
            tmp = tmp.next;
        }
        return arr;
    }
}


