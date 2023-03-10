package cz.antoninvf.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Named
@RequestScoped
public class LoginBean {
    private String salt = "mmm_salty";

    public ArrayList<User> getRegisteredUsers() {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3309/jee_autorizace?user=root&password=heslo");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user")
        ) {
            ArrayList<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("userId"),
                        resultSet.getString("fullName"),
                        resultSet.getString("email"),
                        resultSet.getString("hashedPassword"),
                        resultSet.getDate("createdAt"),
                        resultSet.getDate("updatedAt")
                ));
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isUserRegistered(String email) {
        ArrayList<User> users = getRegisteredUsers();
        for (User user : users) if (user.getEmail().equals(email)) return true;
        return false;
    }

    public void registerUser(String fullName, String email, String password) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3309/jee_autorizace?user=root&password=heslo");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO user (fullName, email, hashedPassword, createdAt, updatedAt) VALUES (?,?,?,?,?)")
        ) {
            String hashedPassword = DigestUtils.sha256Hex(password + salt);

            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, hashedPassword);
            statement.setString(4, LocalDateTime.now().toString());
            statement.setString(5, LocalDateTime.now().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLoginValid(String email, String password) {
        ArrayList<User> users = getRegisteredUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getHashedPassword().equals(DigestUtils.sha256Hex(password + salt))) return true;
        }
        return false;
    }
}
