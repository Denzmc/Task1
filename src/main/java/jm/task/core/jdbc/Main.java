package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Denis","Zagirov", (byte) 36);
//        service.saveUser("Salma","Hayek", (byte) 54);
//        service.saveUser("Miki","Ward", (byte) 57);
//        service.saveUser("Mary","Shir", (byte) 28);
//        service.removeUserById(1);
//        service.cleanUsersTable();
//        service.dropUsersTable();
        List<User> users = service.getAllUsers();
//        System.out.println(users);

    }
}
