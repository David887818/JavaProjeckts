package collection;

import collection.model.Post;

import java.util.Comparator;

public class CompareTo implements Comparator<Post> {

    @Override
    public int compare(Post o1, Post o2) {
        return o2.getCreatedDate().compareTo(o1.getCreatedDate());
    }
}
