package app.dbObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type",nullable = false)
    private String type;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<Book>();

    public Category(){

    }

    public Category(String type){
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<Book> getBooks() {
        return books;
    }
}
