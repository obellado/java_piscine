public class User {
    private static Integer UserIDAmount = 0;
    private Integer Identifier;
    private String Name;
    private Integer Balance;

    public User(String name, Integer balance) {
        if (balance >= 0) {
            UserIDAmount++;
            this.Name = name;
            this.Balance = balance;
            this.Identifier = UserIDAmount;
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
    public void setIdentifier(Integer identifier) {
        Identifier = identifier;
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
