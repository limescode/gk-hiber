package pl.limescode.hiber.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.limescode.hiber.entity.Customer;
import pl.limescode.hiber.factory.SessionFactoryService;

import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final SessionFactoryService sessionFactoryService;

    public CustomerDaoImpl(SessionFactoryService sessionFactoryService) {
        this.sessionFactoryService = sessionFactoryService;
    }

    @Override
    public Customer findById(Long id) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public List<Customer> findAll() {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("select c from Customer c").getResultList();
            session.getTransaction().commit();
            return customers;
        }
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            Customer customer = session.get(Customer.class, id);
            session.delete(customer);
            session.getTransaction().commit();
        }
    }

}
