package pl.limescode.hiber.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import pl.limescode.hiber.entity.Product;
import pl.limescode.hiber.factory.SessionFactoryService;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private final SessionFactoryService sessionFactoryService;

    public ProductDaoImpl(SessionFactoryService sessionFactoryService) {
        this.sessionFactoryService = sessionFactoryService;
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public Product saveOrUpdate(Product product) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactoryService.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
        }
    }

}
