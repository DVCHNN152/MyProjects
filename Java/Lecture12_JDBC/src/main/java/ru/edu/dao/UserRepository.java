package ru.edu.dao;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepository {

    private final Connection connection;

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<User> findById(String id){
        String sql = "select * from users where id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()==false){
                    return Optional.empty();
                }
                User user = extractUserFromResulySet(resultSet);
                return Optional.of(user);

            }catch (Exception ex){
                throw  new RuntimeException(id,ex) ;
            }

        }catch (Exception ex){
           throw  new RuntimeException(id,ex) ;
        }
    }



    public List<User> findAll() {
        //здесь я должен объявить результат
        List<User> users= new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from users")) {

            while (resultSet.next() == true) {

                User user = extractUserFromResulySet(resultSet);
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public User createUser(User user){

        //пишем запрос на создание пользователя

        String sql = "INSERT INTO users ('id', 'first_name','last_name','birth_date','last_access_time','is_active')"+
                "VALUES(?, ?, ?, ?, ?, ?)";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            //устанавливаем значение параметров  исохраняем базу
            statement.setString(1, user.getId().toString());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getBirthDate().toString());
            statement.setString(5, user.getLastAccessTime().toString());
            statement.setBoolean(6, user.isActive);

            statement.executeUpdate();

        }catch (Exception ex){
            throw new RuntimeException("Failed to create user" + user, ex);//указываю что эксепшен породил данное исключение)
        }
        return user;
    }

    public User upDate(User user){

        String sql = "UPDATE users SET first_name = ?, last_name=?, birth_date=?, last_access_time=?,is_active = ? "+
                "WHERE id = ?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            //устанавливаем значение параметров  исохраняем базу

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getBirthDate().toString());
            statement.setString(4, user.getLastAccessTime().toString());
            statement.setBoolean(5, user.isActive);

            statement.setString(6, user.getId().toString());

            statement.executeUpdate();

        }catch (Exception ex){
            throw new RuntimeException("Failed to update user" + user, ex);//указываю что эксепшен породил данное исключение)
        }
        return user;

    }

    public User deleteById(String id){
        User foundUser = findById(id).orElseThrow(() -> {
            return new IllegalStateException("User id=" + id + "not found");
        });
        String sql = "delete from users " +
                "WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,foundUser.getId().toString());
            statement.executeUpdate();
            return foundUser;

        }catch (Exception ex){
            throw new RuntimeException("Failed to deleteBYId id = " + id + ex);
        }
    }


    private User extractUserFromResulySet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String birthDate = resultSet.getString("birth_date");
        String lastAccessTime = resultSet.getString("last_access_time");
        boolean isActive = resultSet.getBoolean("is_active");

        User user = new User();
        user.setId(UUID.fromString(id));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(LocalDate.parse(birthDate));
        user.setLastAccessTime(LocalDateTime.parse(lastAccessTime));
        user.setActive(isActive);

        return user;

    }
}
