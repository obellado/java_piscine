import java.util.Arrays;

public class UsersArrayList implements UsersList {
    private int arraySize;
    private User[] Array;

    UsersArrayList() {
        this.arraySize = 10;
        this.Array = new User[10];
    }

    public int size() {
        return (arraySize);
    }

    public class UserNotFoundException extends RuntimeException {

    }

    @Override
    public void addUser(User user) {
        for (int i = 0; i < arraySize; i++) {
            if (Array[i] == null) {
                Array[i] = user;
                break ;
            }
            if (i == (arraySize - 1)) {
                int n = arraySize + (int)(arraySize / 2);
                User[] tmp = new User[n];
                for (int j = 0; j < arraySize; j++) {
                    tmp[j] = Array[j];
                }
                this.Array = tmp;
                this.arraySize = n;
            }
        }
    }

    @Override
    public User retrieveByID(int id) {
        for (int i = 0; i < arraySize; i++) {
            if (Array[i] != null && Array[i].getIdentifier().equals(id)) {
                return Array[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User retrieveByIndex(int i) {
        if (i < this.arraySize)
            return Array[i];
        return null;
    }

    @Override
    public int retrieveNumber() {
        int n = 0;
        for (int i = 0; i < arraySize; i++) {
            if (Array[i] != null)
                n++;
        }
        return n;
    }

    @Override
    public String toString() {
        return "UsersArrayList{" +
                "arraySize=" + arraySize +
                ", Array=" + Arrays.toString(Array) +
                '}';
    }
}
