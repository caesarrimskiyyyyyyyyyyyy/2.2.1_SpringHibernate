package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User us join fetch us.car")
                .getResultList();
    }

    @Override
    public User getUserByMachine(long series, String model) {
        return (User) sessionFactory
                .getCurrentSession()
                .createQuery("from User us join fetch us.car where us.car.model = :model and us.car.series = :series")
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }
}
