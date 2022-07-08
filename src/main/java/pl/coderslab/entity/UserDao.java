package pl.coderslab.entity;

import org.springframework.security.crypto.bcrypt.BCrypt;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private static final String CREATE_USER_QUERY   = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
    private static final String READ_USER_QUERY     = "SELECT * FROM users WHERE id=?";
    private static final String UPDATE_USER_QUERY   = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY   = "DELETE FROM users WHERE id = ?";
    private static final String GET_ALL_USERS_QUERY = "SELECT * FROM users";

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User create(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId){
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement readUser = conn.prepareStatement(READ_USER_QUERY);
            readUser.setInt(1, userId);
            ResultSet userResult = readUser.executeQuery();
            if (userResult.next()){
                return new User(userResult.getInt("id"),
                        userResult.getString("username"),
                        userResult.getString("email"),
                        userResult.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int userId){
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement deleteUser = conn.prepareStatement(DELETE_USER_QUERY);
            deleteUser.setInt(1, userId);
            deleteUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User update(User user){
        try (Connection conn = DbUtil.getConnection()){
            PreparedStatement updateUser = conn.prepareStatement(UPDATE_USER_QUERY);
            updateUser.setInt(4, user.getId());
            updateUser.setString(1, user.getUserName());
            updateUser.setString(2, user.getEmail());
            updateUser.setString(3, user.getPassword());
            updateUser.executeUpdate();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> findAll() {
        try (Connection conn = DbUtil.getConnection()) {
            Statement getAllUsers = conn.createStatement();
            ResultSet allUsers = getAllUsers.executeQuery(GET_ALL_USERS_QUERY);
            ArrayList<User> outputList = new ArrayList<>();
            while (allUsers.next()){
                User userToAdd = new User(allUsers.getInt("id"),
                        allUsers.getString("username"),
                        allUsers.getString("email"),
                        allUsers.getString("password"));
                outputList.add(userToAdd);
            }
            return outputList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
