package collection.io;
import collection.model.Comment;
import collection.storage.CommentStorage;
import java.io.*;
import java.util.Map;

public class SerializeUtilComment implements Serializable {
    public static void serializeComment(Map<String,Comment> map) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("tempFileUser.txt"));
        objectOutputStream.writeObject(map);
        objectOutputStream.close();
    }
    public static void deserilazeComment() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("tempFileUser.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Comment comments = (Comment) objectInputStream.readObject();
        CommentStorage.put(comments.getText(), comments);
    }
}

