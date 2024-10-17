package csd214_publications.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;


/**
 * @author fcarella
 */
@Entity
public class Book extends Publication {

    @Basic
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}