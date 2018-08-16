package manager;

import db.DBConnectionProvider;
import model.Post;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PostManager {

    private Connection connection;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    private UserManager userManager;
    private CategoryManager categoryManager;

    public PostManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
        userManager = new UserManager();
        categoryManager = new CategoryManager();
    }

    public void add(Post post) {
        String query = "Insert into post(title,content,createdDate,user_id,category_id,pic_url) values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, SDF.format(post.getCreatedDate()));
            preparedStatement.setLong(4, post.getUser().getId());
            preparedStatement.setLong(5, post.getCategory().getId());
            preparedStatement.setString(6, post.getPicUrl());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                post.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Post getPostById(long id) throws ParseException {
        String query = "Select * from post where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Post.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .content(resultSet.getString(3))
                        .createdDate(SDF.parse(resultSet.getString(4)))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .picUrl(resultSet.getString(7))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Post> getPosts() {
        String query = "Select * from post";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Post> posts = new ArrayList<Post>();
            while (resultSet.next()) {
                Post post = Post.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .content(resultSet.getString(3))
                        .createdDate(SDF.parse(resultSet.getString(4)))
                        .user(userManager.getUserById(resultSet.getInt(5)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(6)))
                        .picUrl(resultSet.getString(7))
                        .build();
                posts.add(post);
            }
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deletePostById(long id) throws ParseException {
        String query = "DELETE from post where id=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            int resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void updatePost(Post post) {
        String query = "UPDATE post SET title = ?, content=?,  category_id= ?,createdDate=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setLong(3, post.getCategory().getId());
            preparedStatement.setString(4, SDF.format(post.getCreatedDate()));
            preparedStatement.setLong(5, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
