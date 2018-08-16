package collection.storage;

import collection.io.SerializeUtilComment;
import collection.model.Comment;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class CommentStorage implements Serializable {

    private static Map<String, Comment> commentMap = new TreeMap<>();

    public static void put(String postTitle, Comment comments)  {
        commentMap.put(postTitle, comments);
        try {
            SerializeUtilComment.serializeComment(commentMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void printCommentsList() {
        for (String s : commentMap.keySet()) {
            System.out.println(s + " " + commentMap.get(s));
        }
    }

}
