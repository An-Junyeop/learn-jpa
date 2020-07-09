package model;

import javax.persistence.*;

@Entity
public class BoardDetail {

    @Id
    private int boardId;

    @MapsId //BoardDetail.boardld 매핑
    @OneToOne
    @JoinColumn (name= "BOARD_ID")
    private Board board;

    private String content;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
