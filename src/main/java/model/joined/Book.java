package model.joined;

import javax.persistence.Entity;

@Entity
//@PrimaryKeyJoinColumn("BOOK_ID") JOINED 방식에서 사용할 컬럼명
//@DiscriminatorValue("B") JOINED, SINGLE_TABLE에 사용
public class Book extends  Item {
    private String author;
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
