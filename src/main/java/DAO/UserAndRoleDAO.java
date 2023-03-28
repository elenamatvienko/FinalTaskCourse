package DAO;

import model.Role;
import model.User;

import java.util.List;

public interface UserAndRoleDAO {

    Long create(User user);

    Long createRole(Role role);

    User readUserById(Long id);

    Role readRoleById(Long id);

    void upDateUser(User user);

    void deleteUser(Long id);

    void deleteRole(Long id);

    List<User> readAll();

}
