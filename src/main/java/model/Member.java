package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private int id;

    private String name;

    @ManyToMany // 다대다 관계설정
    @JoinTable(name = "MEMBER_PRODUCT", // 테이블명
    joinColumns = @JoinColumn(name = "MEMBER_ID"), // 조인할 컬럼
    inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")) // 반대편 조인할 컬럼
    private List<Product> products = new ArrayList<Product>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getMembers().add(this);
    }
}
