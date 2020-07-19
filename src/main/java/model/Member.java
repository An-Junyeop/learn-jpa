package model;

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
@NamedNativeQuery(
        name = "Member.memberWithOrderCount",
        query =
                "SELECT M.MEMBER_ID AS M_ID, " +
                "M.NAME AS M_NAME, " +
                "M.CREATE_DATE AS M_CREATE_DATE, " +
                "M.LAST_MODIFIED_DATE AS M_LAST_DATE, " +
                "M.CITY AS M_CITY, " +
                "M.STREET AS M_STREET, " +
                "M.ZIPCODE AS M_ZIPCODE, " +
                "I.ORDER_COUNT " +
                "FROM MEMBER M " +
                "LEFT JOIN " +
                "   (SELECT MEMBER_ID, COUNT(*) AS ORDER_COUNT " +
                "   FROM ORDERS " +
                "   GROUP BY MEMBER_ID " +
                "   ) I " +
                "ON M.MEMBER_ID = I.MEMBER_ID",
        resultSetMapping = "memberWithOrderCount"
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
