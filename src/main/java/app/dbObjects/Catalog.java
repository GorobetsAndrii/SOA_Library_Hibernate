package app.dbObjects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "catalog")
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "availability", nullable = false)
    private boolean availability;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public Catalog(){
        
    }

    public Catalog(int quantity){
        availability = true;
        this.quantity = quantity;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if(this.quantity == 1) availability = true;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
}
