package manager;

import db.DBConnectionProvider;
import model.Advertisement;
import model.Category;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementManager {
    private Connection connection;

    public AdvertisementManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public List<Advertisement> getAdsById(int id) {
        String query = "Select * from advertisement where id=?";

        String query1 = "Select parent_id from parent where id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            preparedStatement1.setLong(1, id);
            ResultSet resultSet1 = preparedStatement.executeQuery();
            List<Category> categoryList = new ArrayList<Category>();
            CategoryManager categoryManager = new CategoryManager();

            while (resultSet1.next()) {
                try {
                    categoryList.add(categoryManager.getCategoryById(resultSet1.getInt(1)));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                List<Advertisement> advertisementManagerList = new ArrayList<Advertisement>();
                User user = new User();
                while (resultSet.next()) {
                    UserManager userManager = new UserManager();
                    Advertisement advertisement = Advertisement.builder()
                            .id(resultSet.getInt(1))
                            .title(resultSet.getString(2))
                            .description(resultSet.getString(3))
                            .price(resultSet.getInt(5))
                            .picUrl(resultSet.getString(6))
                            .author(userManager.getUserById(resultSet.getInt(7)))
                            .build();
                    advertisementManagerList.add(advertisement);

                }
                return advertisementManagerList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void add(Advertisement advertisement) {
        String query = "Insert into advertisement(title,description,price,pic_url,author_id,category_id) values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, advertisement.getTitle());
            preparedStatement.setString(2, advertisement.getDescription());
            preparedStatement.setInt(3, advertisement.getPrice());
            preparedStatement.setString(4, advertisement.getPicUrl());
            preparedStatement.setInt(5, advertisement.getAuthor().getId());
            preparedStatement.setLong(6, advertisement.getCategory().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                advertisement.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Advertisement> getAdvertisements() {

        String query = "Select * from advertisement ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Advertisement> advertisements = new ArrayList<Advertisement>();
            while (resultSet.next()) {
                User user = new User();
                UserManager userManager = new UserManager();
                CategoryManager categoryManager = new CategoryManager();
                Advertisement advertisement = Advertisement.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .price(resultSet.getInt(4))
                        .picUrl(resultSet.getString(5))
                        .author(user = userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build();
                advertisements.add(advertisement);
            }
            return advertisements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void deleteById(int parseInt) {
        String query = "DELETE * from advertisement WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, parseInt);
            int resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    public List<Advertisement> getAdvertisementsByCategoryId(int i) {

        String query = "Select * from advertisement WHERE category_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,i);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Advertisement> advertisements = new ArrayList<Advertisement>();
            while (resultSet.next()) {
                User user = new User();
                UserManager userManager = new UserManager();
                CategoryManager categoryManager = new CategoryManager();
                Advertisement advertisement = Advertisement.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .price(resultSet.getInt(4))
                        .picUrl(resultSet.getString(5))
                        .author(user = userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build();
                advertisements.add(advertisement);
            }
            return advertisements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
