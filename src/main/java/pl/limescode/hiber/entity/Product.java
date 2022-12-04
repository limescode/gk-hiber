package pl.limescode.hiber.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(schema = "shop", name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Product(String title, Integer price) {
        this.title = title;
        this.price = price;
    }

    public void print() {
        System.out.println(
                "Product{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        ", price=" + price +
                        '}'
        );
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
