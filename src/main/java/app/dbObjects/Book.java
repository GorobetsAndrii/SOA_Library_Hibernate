package app.dbObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToOne(mappedBy = "book",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Catalog catalog;

    @OneToMany(mappedBy = "book",cascade = {CascadeType.REMOVE,CascadeType.MERGE},fetch = FetchType.EAGER)
    private List<Lend> lend;

    public Book(){

    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setLend(List<Lend> lend) {
        this.lend = lend;
    }

    public List<Lend> getLend() {
        return lend;
    }
}