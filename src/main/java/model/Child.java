package model;

import javax.persistence.*;

@Entity
//@IdClass(ChildId.class)
public class Child {

    @EmbeddedId
    private ChildId id;

    //@Id
    @MapsId("parentId")
    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public Parent parent;

    /*@Id
    @Column(name = "CHILD_ID")
    private int childId;*/

    private String name;

    public ChildId getId() {
        return id;
    }

    public void setId(ChildId id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
