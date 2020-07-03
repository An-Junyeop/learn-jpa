package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint( // 제약 조건 (UniqueConstraint 는 유니크 설정)
        name = "NAME_AGE_UNIQUE", // 설정 이름
        columnNames = { // 컬럼 설정
                "NAME",
                "AGE"
        }
)})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    // NOT NULL, 길이 10 설정
    @Column(name = "NAME", nullable = false, length = 10)
    private String name;

    private Integer age;

    /* 다양한 매핑 어노테이션 사용 */
    // Enumerated 을 사용한 매핑 어노테이션
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // Temporal 을 사용한 날짜 매핑
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // Lob 을 사용한 CLOB, BLOB 같은 긴 데이터를 매핑
    @Lob
    private String description;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", roleType=" + roleType +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
