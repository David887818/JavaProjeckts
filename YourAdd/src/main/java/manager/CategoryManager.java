package manager;

import db.DBConnectionProvider;
import model.Category;
import model.User;
import model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private Connection connection;

    public CategoryManager(){
        connection= DBConnectionProvider.getInstance().getConnection();
    }

    public List<Category>  getCategoryes() {

        String query = "Select * from category ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> categoryList=new ArrayList<Category>();
            while (resultSet.next()) {
                Category category= Category.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build();
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

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

    public Category getCategoryById(int id) {
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


}
