package ru.edu.renderer;

import ru.edu.dao.User;
import ru.edu.dao.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class App 
{
    public static void main( String[] args )
    {
        String dbPath = "C:\\Users\\DMTR\\Desktop\\ДЗ\\Lecture_12_JDBC\\Chinook.db";
        String jdbcUrl = "jdbc:sqlite:"+ dbPath;

        try(Connection connection = DriverManager.getConnection(jdbcUrl)) {
            UserRepository repository = new UserRepository(connection);
            /*

            User user=new User();
            user.setId(UUID.randomUUID());
            user.setFirstName("Anton");
            user.setLastName("Ivanov");
            user.setBirthDate(LocalDate.of(2000,10,2));
            user.setLastAccessTime(LocalDateTime.now());
            user.setActive(true);

          repository.createUser(user);

             */


            User foundUser = repository.findById("jsfgkjsdklg-sfgdsf-sfg-sdfg").orElse(null);
            foundUser.setActive(true);
            foundUser.setLastAccessTime(LocalDateTime.now());

            List<User> users = repository.findAll();
            System.out.println(users);


            User deleteUser = repository.deleteById("sdfsdffs-fs-df-sdf-s");




        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
