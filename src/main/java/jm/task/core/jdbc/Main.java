package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;



public class Main {



    public static void main(String[] args)  {
        Util.getInstance().getConnection();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Name1", "Last1", (byte) 1);
        userService.saveUser("Name2", "Last2", (byte) 2);
        userService.saveUser("Name3", "Last3", (byte) 3);
        userService.saveUser("Name4", "Last4", (byte) 4);

        userService.removeUserById(2);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}


