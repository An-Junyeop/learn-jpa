package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ChildId implements Serializable {

    // Child.parent 매핑
    //private int parent;

    // Child의 MapsId 매핑
    private int parentId;

    // Child.childId 매핑
    //private int childId;

    @Column(name = "CHILD_ID")
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildId childId = (ChildId) o;
        return parentId == childId.parentId &&
                id == childId.id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
