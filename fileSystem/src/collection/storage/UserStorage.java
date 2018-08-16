package collection.storage;

import collection.io.SerializeUtilUser;
import collection.model.MyException;
import collection.model.User;


import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

public class UserStorage implements Serializable {

    private static Map<String, User> userMap = new TreeMap<>();

    public static void put(String userName, User user) {
        userMap.put(userName, user);
        try {
            SerializeUtilUser.serializeUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printUserList() {
        for (String users : userMap.keySet()) {
            System.out.println(users);

        }
    }

    public User getUserByEmailAndPassword(String email, String password) throws MyException {
        for (String integer : userMap.keySet()) {
            if (userMap.get(integer).getEmail().equals(email) && userMap.get(integer).getPassword().equals(password)) {
                return userMap.get(integer);
            }
        }
        throw new MyException();
    }

    public boolean isEmailExist(String email) {
        for (String users : userMap.keySet()) {
            if (userMap.get(users).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
