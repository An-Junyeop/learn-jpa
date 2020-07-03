package model;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "BOARD_SEQ_GENERATOR", // 설정 이름
        sequenceName = "BOARD_SEQ", // 시퀀스 이름
        initialValue = 1, // 초기값
        allocationSize = 1 // 증가값
)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "BOARD_SEQ_GENERATOR")
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
