package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARENT_ID")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "parent")
    private List<Child> child = new ArrayList<>();

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

    public List<Child> getChild() {
        return child;
    }

    public void setChild(List<Child> child) {
        this.child = child;
    }
}
