package pl.limescode.hiber;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.limescode.hiber.entity.Customer;
import pl.limescode.hiber.entity.Order;
import pl.limescode.hiber.entity.Product;
import pl.limescode.hiber.factory.SessionFactoryService;
import pl.limescode.hiber.service.OrderService;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext("pl.limescode.hiber");

        SessionFactoryService sessionFactoryService = context.getBean(SessionFactoryService.class);
        sessionFactoryService.init();
        OrderService orderService = context.getBean(OrderService.class);

        try {
            System.out.println("===================================");
            List<Product> productsFor1L = orderService.findProductsByCustomer(1L);
            System.out.println("Customer id = 1L, size: " + productsFor1L.size() + ", products: " + productsFor1L);

            System.out.println("===================================");
            List<Product> productsFor2L = orderService.findProductsByCustomer(2L);
            System.out.println("Customer id = 2L, size: " + productsFor1L.size() + ", products: " + productsFor2L);

            System.out.println("===================================");
            List<Customer> customersFor3L = orderService.findCustomersByProduct(3L);
            System.out.println("Product id = 3L, size: " + customersFor3L.size() + ", customers: " + customersFor3L);

            System.out.println("===================================");
            Order order = orderService.findById(1L);
            order.print();

            System.out.println("Press enter to exit");
            Scanner myObj = new Scanner(System.in);

            myObj.nextLine();
            System.out.println("App is gonna be closed");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryService.shutdown();
        }

    }
}
