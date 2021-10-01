import java.util.UUID;

enum TransferCategory {
    DEBITS,
    CREDITS;
}

public class Transaction {
    private UUID Identifier;
    private User Recipient;
    private User Sender;
    private TransferCategory transferCategory;
    private Integer transferAmount;

    public Transaction(User sender, User recipient, TransferCategory transferCat, Integer amount){
        if (transferCat == TransferCategory.CREDITS)
        this.Identifier = UUID.randomUUID();
        this.Recipient = recipient;
        this.Sender = sender;
        this.transferCategory = transferCat;
        this.transferAmount = amount;
    }

    public Integer getTransferAmount() {return transferAmount;}
    public TransferCategory getTransferCategory() { return transferCategory; }
    public User getRecipient() {        return Recipient;    }
    public User getSender() {        return Sender;    }
    public UUID getIdentifier() {  return Identifier;    }

    public void setRecipient(User recipient) { Recipient = recipient; }
    public void setSender(User sender) { Sender = sender; }
    public void setTransferAmount(Integer transferAmount) { this.transferAmount = transferAmount; }
    public void setTransferCategory(TransferCategory transferCategory) {this.transferCategory = transferCategory;}
    public void setIdentifier(UUID identifier) { Identifier = identifier; }

    @Override
    public String toString() {
        return "Transaction{" +
                "Identifier=" + Identifier +
                ", Recipient=" + Recipient +
                ", Sender=" + Sender +
                ", transferCategory=" + transferCategory +
                ", transferAmount=" + transferAmount +
                '}';
    }
}
