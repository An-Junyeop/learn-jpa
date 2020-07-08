package model;

import java.io.Serializable;

public class GrandChildId implements Serializable {

    private ChildId child; // GrandChild.child 매핑

    private int id; // GrandChild.id 매핑

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public ChildId getChildId() {
        return child;
    }

    public void setChildId(ChildId child) {
        this.child = child;
    }

    public ChildId getChild() {
        return child;
    }

    public void setChild(ChildId child) {
        this.child = child;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
