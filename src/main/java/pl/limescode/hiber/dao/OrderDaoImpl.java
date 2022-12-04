package pl.limescode.hiber.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.limescode.hiber.entity.Customer;
import pl.limescode.hiber.entity.Order;
import pl.limescode.hiber.entity.Product;
import pl.limescode.hiber.factory.SessionFactoryService;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final SessionFactoryService sessionFactoryService;

    public OrderDaoImpl(SessionFactoryService sessionFactoryService) {
        this.sessionFactoryService = sessionFactoryService;
    }

    @Override
    public Order findById(Long id) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.getTransaction().commit();
            return order;
        }
    }

    @Override
    public List<Order> findAll() {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            List<Order> orders = session.createQuery("select o from Order o").getResultList();
            session.getTransaction().commit();
            return orders;
        }
    }

    @Override
    public Order saveOrUpdate(Order order) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
            return order;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.delete(order);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Product> findProductsByCustomerId(Long customerId) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p inner join Order o " +
                    "on p.id = o.product.id where o.customer.id = '" + customerId + "'").getResultList();
            return products;
        }
    }

    @Override
    public List<Customer> findCustomersByProductId(Long productId) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            List<Customer> customers = session.createQuery("select c from Customer c inner join Order o " +
                    "on c.id = o.customer.id where o.product.id = '" + productId + "'").getResultList();
            return customers;
        }
    }
}
