package DAO.Impl;

import DAO.UserAndRoleDAO;
import hibernate.HibernateSessionFactoryUtil;
import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserAndRoleDAOImpl implements UserAndRoleDAO {

    @Override
    public Long create(User user) {
        Long result;
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            result = (Long) session.save(user);
            transaction.commit();
        }
        return result;
    }

    @Override
    public Long createRole(Role role) {
        Long result;
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            result = (Long)session.save(role);
            transaction.commit();
        }
        return result;
    }

    @Override
    public User readUserById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            User user = session.get(User.class, id);
            user.getRoles().size();
            return user;
        }

    }

    @Override
    public Role readRoleById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Role role = session.get(Role.class, id);
            role.getUsers().size();
            return role;
        }
    }

    @Override
    public void upDateUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }

    }

    @Override
    public void deleteUser(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        }

    }
    @Override
    public void deleteRole(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Role role = session.get(Role.class, id);
            session.remove(role);
            transaction.commit();
        }

    }

    @Override
    public List<User> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            List<User> user = session.createQuery("from User", User.class).list();
            transaction.commit();
            return user;
        }
    }

}
