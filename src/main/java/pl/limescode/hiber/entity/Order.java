package pl.limescode.hiber.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "shop", name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created")
    private LocalDateTime created;

    public Order(Customer customer, Product product, Integer price, Integer quantity) {
        this.customer = customer;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.created = LocalDateTime.now();
    }

    public void print() {
        System.out.println(
                "Order{" +
                        "id=" + id +
                        ", customer='" + customer.toString() + '\'' +
                        ", product='" + product.toString() + '\'' +
                        ", price=" + price +
                        ", quantity=" + quantity +
                        '}'
        );
    }

}
