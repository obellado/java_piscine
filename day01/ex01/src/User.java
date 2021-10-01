public class User {
    private Integer Identifier;
    private String Name;
    private Integer Balance;

    public User(String name, Integer balance) {
        if (balance >= 0) {
            this.Name = name;
            this.Balance = balance;
            //System.err.println("User constructor");
            this.Identifier = UserIdsGenerator.getInstance().generateId();
        } else
            throw new ExceptionInInitializerError();
            //System.err.println("Cannot create User with negative Balance");
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
    public void setBalance(Integer balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "Identifier=" + Identifier +
                ", Name='" + Name + '\'' +
                ", Balance=" + Balance +
                '}';
    }
}
