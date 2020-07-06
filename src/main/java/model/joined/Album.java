package model.joined;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("A") JOINED, SINGLE_TABLE에 사용
public class Album extends Item {

    private String artist;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
