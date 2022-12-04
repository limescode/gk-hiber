package pl.limescode.hiber.dao;
import pl.limescode.hiber.entity.Customer;

import java.util.List;

public interface CustomerDao {

    Customer findById(Long id);

    List<Customer> findAll();

    Customer saveOrUpdate(Customer customer);

    void deleteById(Long id);
}
