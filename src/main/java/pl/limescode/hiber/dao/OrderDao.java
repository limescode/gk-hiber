package pl.limescode.hiber.dao;

import pl.limescode.hiber.entity.Customer;
import pl.limescode.hiber.entity.Order;
import pl.limescode.hiber.entity.Product;

import java.util.List;

public interface OrderDao {

    Order findById(Long id);

    List<Order> findAll();

    Order saveOrUpdate(Order order);

    void deleteById(Long id);

    List<Product> findProductsByCustomerId(Long customerId);

    List<Customer> findCustomersByProductId(Long productId);
}
