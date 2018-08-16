package collection.storage;

import collection.CompareTo;
import collection.io.SerializeUtilPost;
import collection.model.MyException;
import collection.model.Post;
import collection.model.User;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostStorage implements Serializable {

    private static List<Post> postList = new ArrayList<>();

    public static void add(Post post)  {
        postList.add(post);
        try {
            SerializeUtilPost.serializePost(post);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPostLists() {
        postList.sort(new CompareTo());
        postList.forEach(System.out::println);
    }

    public void postByUser(User user) {
        for (Post post : postList) {
            if (post.getUser().equals(user)) {
                System.out.println(post);
            }
        }
    }

    public Post getPostByTitle(String str) throws MyException {
        for (Post post : postList) {
            if (post.getTitle().equals(str))
                return post;
        }
        throw new MyException();
    }

    public void deletePost(String title) {
        for (Post post : postList) {
            if (post.getTitle().equals(title)) {
                postList.remove(post);
                System.out.println("Post is deleted ");
            }
        }
    }


    public void searchByCategory(String category) {
        for (Post posts : postList) {
            if (posts.getCategories().name().equals(category)) {
                System.out.println(posts);
            }
        }
    }
}
