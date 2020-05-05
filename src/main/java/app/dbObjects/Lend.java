package app.dbObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "lends")
public class Lend implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    @Column(name = "lendTime")
    private Timestamp lendTime;

    @Column(name = "returnTime")
    private Timestamp returnTime;

    public Lend(){
        lendTime = new Timestamp(System.currentTimeMillis());
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setLendTime(Timestamp lendTime) {
        this.lendTime = lendTime;
    }

    public void setReturnTime(Timestamp returnTime) {
        this.returnTime = returnTime;
    }

    public Timestamp getLendTime() {
        return lendTime;
    }

    public Timestamp getReturnTime() {
        return returnTime;
    }
}
