package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        userService.createUsersTable();

        User user1 = new User("Andrew", "Fokeev", (byte) 28);
        User user2 = new User("Ice", "Cube", (byte) 54);
        User user3 = new User("Steve", "Jobs", (byte) 12);
        User user4 = new User("Alexander", "Pushkin", (byte) 14);

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        userService.cleanUsersTable();

    }
    }
