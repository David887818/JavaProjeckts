package collection.io;
import collection.model.User;
import collection.storage.UserStorage;
import java.io.*;
public class SerializeUtilUser implements Serializable {
    public static void serializeUser(User user) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("tempFileUser.txt"));
        objectOutputStream.writeObject(user);
        objectOutputStream.close();
    }
    public static void deserilazeUser() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("tempFileUser.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        User user1 = (User) objectInputStream.readObject();
        UserStorage.put(user1.getName(), user1);
    }
}
