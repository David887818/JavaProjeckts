package manager;

import db.DBConnectionProvider;
import model.Message;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private Connection connection;

    public MessageManager() {
        connection= DBConnectionProvider.getInstance().getConnection();
    }

    public List<Message> getMessageById(int id) {
        String query = "Select * from message where to_id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Message> messageList=new ArrayList<Message>();
            User user=new User();
            User user1=new User();
            while (resultSet.next()) {
                UserManager userManager=new UserManager();
                 Message messages= Message.builder()
                        .id(resultSet.getInt(1))
                        .from(user=userManager.getUserById(resultSet.getInt(2)))
                        .to(user1=userManager.getUserById(resultSet.getInt(3)))
                        .text(resultSet.getString(4))
                        .build();
                 messageList.add(messages);

            }
            return messageList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(Message message){
        String query = "Insert into message(from_id,to_id,text) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,message.getFrom().getId());
            preparedStatement.setInt(2,message.getTo().getId());
            preparedStatement.setString(3,message.getText());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                message.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
