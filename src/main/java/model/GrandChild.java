package model;

import javax.persistence.*;

@Entity
@Table(name = "GRAND_CHILD")
//@IdClass(GrandChildId.class)
public class GrandChild {

    @EmbeddedId
    private GrandChildId id;

    //@Id
    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child child;

    /*@Id
    @Column(name = "GRANDCHILD_ID")
    private int id;*/

    private String name;

    public GrandChildId getId() {
        return id;
    }

    public void setId(GrandChildId id) {
        this.id = id;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
