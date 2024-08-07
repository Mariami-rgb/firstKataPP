package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Array;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Daniil", "Shabalin", (byte) 23);
        userService.saveUser("Nikolay", "Shabalin", (byte) 14);
        userService.saveUser("Viktor", "Shabalin", (byte) 60);
        userService.saveUser("Aleksey", "Shabalin", (byte) 44);
        userService.removeUserById(2);
        for (User user: userService.getAllUsers()){
            System.out.println(user);// реализуйте алгоритм здесь
        }
        userService.cleanUsersTable();
    }
}
