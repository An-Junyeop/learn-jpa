package model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides({ // 상속받는 컬럼의 이름을 재정의
        @AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID")),
        @AttributeOverride(name = "name", column = @Column(name = "MEMBER_NAME"))
})
public class Member extends BaseEntity {

    // BaseEntity를 상속받아 ID, NAME은 이미 있다

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
