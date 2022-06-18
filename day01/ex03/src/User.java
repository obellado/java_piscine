public class User {
    private Integer Identifier;
    private String Name;
    private Integer Balance;
    private TransactionsList transactions;

    public User(String name, Integer balance) {
        if (balance >= 0) {
            this.Name = name;
            this.Balance = balance;
            this.Identifier = UserIdsGenerator.getInstance().generateId();
        } else
            throw new ExceptionInInitializerError();
    }
    public User(){
        this.Identifier = UserIdsGenerator.getInstance().generateId();
    }

    public Integer getBalance() {
        return Balance;
    }
    public Integer getIdentifier() {
        return Identifier;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setBalance(Integer balance) throws Exception {
        if (balance < 0)
            throw new Exception();
        Balance = balance;
    }
    public TransactionsList getTransactionsList() {
        return transactions;
    }
    public void setTransactionsList(TransactionsList Transactions) {
        transactions = Transactions;
    }

    @Override
    public String toString() {
        return "User{" +
                "Identifier=" + Identifier +
                ", Name='" + Name + '\'' +
                ", Balance=" + Balance +
                "}\n";
    }
}
