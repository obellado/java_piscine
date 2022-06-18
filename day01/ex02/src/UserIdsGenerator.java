public final class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private int Id;

    private UserIdsGenerator(){
        Id = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return (instance);
    }
    public int generateId() {
        Id = Id + 1;
        return (Id);
    }
}
