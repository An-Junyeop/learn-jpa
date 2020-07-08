package model;

import javax.persistence.*;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID1"),
            @JoinColumn(name = "PARENT_ID2")
    })
    private Parent parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
