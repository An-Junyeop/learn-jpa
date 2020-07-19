package model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SqlResultSetMapping(name = "memberWithOrderCount",
        entities = {
            @EntityResult(entityClass = model.Member.class, fields = {
                    @FieldResult(name = "id", column = "m_id"),
                    @FieldResult(name = "name", column = "m_name"),
                    @FieldResult(name = "createDate", column = "m_create_date"),
                    @FieldResult(name = "lastModifiedDate", column = "m_last_date"),
                    @FieldResult(name = "address.city", column = "m_city"),
                    @FieldResult(name = "address.street", column = "m_street"),
                    @FieldResult(name = "address.zipcode", column = "m_zipcode")
            })
        },
        columns = {@ColumnResult(name = "ORDER_COUNT")}
)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);

        if(order.getMember() != this) {
            order.setMember(this);
        }
    }
}
