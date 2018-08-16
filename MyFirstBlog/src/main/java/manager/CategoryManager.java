package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private Connection connection;

    public CategoryManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void add(Category category) {
        String query = "Insert into category(name) values(?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(long id) {
        String query = "Select * from category where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getCategories() {
        String query = "Select * from category";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Category> categories = new ArrayList<Category>();
            while (resultSet.next()) {
                Category build = Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
                categories.add(build);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
