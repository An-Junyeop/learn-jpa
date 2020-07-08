package model;

import javax.persistence.*;

@Entity
//@IdClass(ParentId.class)
public class Parent {

    /*@Id
    @Column(name = "PARENT_ID1")
    // 복합키에는 GenerateValue를 사용할 수 없다
    private int id1;

    @Id
    @Column(name = "PARENT_ID2")
    private int id2;*/

    @EmbeddedId
    private ParentId id;

    private String name;

    public ParentId getId() {
        return id;
    }

    public void setId(ParentId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
