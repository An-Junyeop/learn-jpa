package model;

import javax.persistence.*;

@Entity
@TableGenerator(
        name = "DEVICE_SEQ_GENERATOR", // 설정 이름
        table = "MY_SEQUENCE", // 테이블 이름
        pkColumnName = "DEVICE_SEQ", // 컬럼 명
        allocationSize = 1 // 증가값
)
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
    generator = "DEVICE_SEQ_GENERATOR")
    private Long id;

    @Column
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
