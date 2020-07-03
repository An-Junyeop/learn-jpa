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
    /*Enumerated 을 사용한 매핑 어노테이션
    * ORDINAL 로 설정할 경우 값이 Enum 에서 정의된 순서로 데이터베이스에 저장됨
    * ADMIN, USER 사이에 GUEST 같이 새로운 값이 들어올 경우 순서 값이 바뀌기 때문에
    * 데이터에 문제가 발생할 수 있다
    * */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /*Temporal 을 사용한 날짜 매핑
    * DATE => 날짜
    * TIME => 시간
    * TIMESTAMP => 날짜와 시간
    * */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /*CLOB(String, char[], java.sql.CLOB)
    * BLOB(byte[], java.sql.BLOB)
    * 데이터를 매핑
    * */
    @Lob
    private String description;

    // 매핑하지 않음
    @Transient
    private Integer temp;

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

    /* 접근자를 이용하여 데이터에 접근한다.
    * @Id 가 필드에 있으므로 기본은 필드 접근 방식(필드의 값으로 저장)
    * 별개로 접근자에 Access 지정 시 해당 컬럼만 프로퍼티 접근 방식
    * 때문에 AGE + 5 값이 저장된다.
    * */
    @Access(AccessType.PROPERTY)
    public Integer getAge() {
        return age + 5;
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

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
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
