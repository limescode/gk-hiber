package pl.limescode.hiber.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.limescode.hiber.dao.OrderDao;
import pl.limescode.hiber.entity.Customer;
import pl.limescode.hiber.entity.Order;
import pl.limescode.hiber.entity.Product;

import java.util.List;

@AllArgsConstructor
@Repository
public class OrderService {

    private final OrderDao orderDao;

    public Order findById(Long id) {
        return orderDao.findById(id);
    }

    public List<Product> findProductsByCustomer(Long id) {
        return orderDao.findProductsByCustomerId(id);
    }

    public List<Customer> findCustomersByProduct(Long id) {
        return orderDao.findCustomersByProductId(id);
    }
}
