import DAO.Impl.UserAndRoleDAOImpl;
import DAO.UserAndRoleDAO;
import model.Role;
import model.User;

import java.util.List;


public class Application {
    public static void main(String[] args) {

   // ДОБАВЛЯТЬ НОВОГО ПОЛЬЗОВАТЕЛЯ С РОЛЯМИ В БД.
                UserAndRoleDAO userAndRoleDAO = new UserAndRoleDAOImpl();

        Role role1 = new Role("Role1");
        Role role2 = new Role("Role2");

        User user1 = new User("User1", "Login1", "Password1");
        User user2 = new User("User2", "Login2", "Password2");
        User user3 = new User("User3", "Login3", "Password3");

        Long idRole1 = userAndRoleDAO.createRole(role1);

        user1.setRoles(List.of(role1, role2));
        user2.setRoles(List.of(role1));
        user3.setRoles(List.of(role1));

        Long id1 = userAndRoleDAO.create(user1);
        Long id2 = userAndRoleDAO.create(user2);
        Long id3 = userAndRoleDAO.create(user3);

        System.out.println("ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ ИЗ БД (БЕЗ РОЛЕЙ)");
        System.out.println(userAndRoleDAO.readAll());
        System.out.println("ПОЛУЧАТЬ КОНКРЕТНОГО ПОЛЬЗОВАТЕЛЯ (С ЕГО РОЛЯМИ) ИЗ БД");
        System.out.println(userAndRoleDAO.readUserById(id1) + " " + userAndRoleDAO.readUserById(id1).getRoles());

   //  РЕДАКТИРОВАТЬ СУЩЕСТВУЮЩЕГО ПОЛЬЗОВАТЕЛЯ В БД.
        user2.setUserLogin("Login2*2");
        userAndRoleDAO.upDateUser(user2);
        System.out.println("ПРОВЕРКА ИЗМЕНЕНИЙ");
        System.out.println(userAndRoleDAO.readUserById(id2));


        System.out.println("ПОЛУЧИТЬ СПИСОК ПОЛЬЗОВАТЕЛЕЙ ПО КОНКРЕТНОЙ РОЛИ");
        role1 = userAndRoleDAO.readRoleById(idRole1);
        role1.getUsers().forEach(System.out::println);

// УДАЛЯТЬ ПОЛЬЗОВАТЕЛЯ В БД
        user1 = userAndRoleDAO.readUserById(id1);
        user1.getRoles().clear();
        userAndRoleDAO.upDateUser(user1);

        user2 = userAndRoleDAO.readUserById(id2);
        user2.getRoles().clear();
        userAndRoleDAO.upDateUser(user2);

        user3 = userAndRoleDAO.readUserById(id3);
        user3.getRoles().clear();
        userAndRoleDAO.upDateUser(user3);

        userAndRoleDAO.deleteUser(id1);
        userAndRoleDAO.deleteUser(id2);
        userAndRoleDAO.deleteUser(id3);

    }
}

