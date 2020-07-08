package model;

import java.io.Serializable;

public class ChildId implements Serializable {

    // Child.parent 매핑
    private int parent;

    // Child.childId 매핑
    private int childId;

    public ChildId() {}

    public ChildId(int parent, int childId) {
        this.parent = parent;
        this.childId = childId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }
}
