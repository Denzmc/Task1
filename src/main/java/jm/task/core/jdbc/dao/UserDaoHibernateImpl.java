package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            String query = "create table IF NOT EXISTS users ("  +
                    "    id SERIAL PRIMARY KEY,"  +
                    "    firstname CHARACTER VARYING(30)," +
                    "    lastname CHARACTER VARYING(30)," +
                    "    age INTEGER" +
                    ")";
            session.createSQLQuery(query).executeUpdate();

            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createSQLQuery("drop table users").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (final Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            String query = "delete User where id =";
            session.createSQLQuery(query + id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list;

        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();

            Query query = session.createQuery("FROM User");
            list = query.list();

            session.getTransaction().commit();
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createQuery("delete User");
            session.getTransaction().commit();
        }
    }
}