package collection.io;
import collection.model.Post;
import collection.storage.PostStorage;
import java.io.*;
public class SerializeUtilPost implements Serializable {
    public static void serializePost(Post post) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("tempFilePost.txt"));
        objectOutputStream.writeObject(post);
        objectOutputStream.close();
    }
    public static void deserilazePost() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("tempFilePost.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Post post = (Post) objectInputStream.readObject();
        PostStorage.add(post);
    }
}


