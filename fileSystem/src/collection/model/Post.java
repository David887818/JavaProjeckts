package collection.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Post implements Serializable {
//        implements Comparable<Post>

    private String title;
    private String post;
    private Enum Categories;
    private Date CreatedDate;
    private User user;
    private Comment comments;

    public Post() {
    }

    public Post(String title, String post, Enum categories, Date createdDate, User user, Comment comments) {
        this.title = title;
        this.post = post;
        Categories = categories;
        CreatedDate = createdDate;
        this.user = user;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Enum getCategories() {
        return Categories;
    }

    public void setCategories(Enum categories) {
        Categories = categories;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", post='" + post + '\'' +
                ", Categorie=" + Categories +
                ", CreatedDate=" + CreatedDate +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post1 = (Post) o;
        return Objects.equals(title, post1.title) &&
                Objects.equals(post, post1.post) &&
                Objects.equals(Categories, post1.Categories) &&
                Objects.equals(CreatedDate, post1.CreatedDate) &&
                Objects.equals(user, post1.user) &&
                Objects.equals(comments, post1.comments);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, post, Categories, CreatedDate, user, comments);
    }

//    @Override
//    public int compareTo(Post o) {
//        return title.compareTo(o.title);
//    }
}
