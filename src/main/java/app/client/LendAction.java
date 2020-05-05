package app.client;

import app.dbObjects.Book;
import app.dbObjects.Lend;
import app.dbObjects.Reader;
import app.interfaces.IDB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean(name = "LendAction")
@SessionScoped
public class LendAction {
    @EJB
    IDB db;

    private String name;
    private String surname;
    private Book book;
    private Reader selectReader;
    private boolean render;

    public String openBooks4Return(Reader reader){
        selectReader = reader;
        return "returnBook";
    }

    public void doReturn(Book book){
        book.getCatalog().setQuantity(book.getCatalog().getQuantity() + 1);
        for(Lend l : book.getLend()){
            if(l.getReturnTime() == null){
                l.setReturnTime(new Timestamp(System.currentTimeMillis()));
                break;
            }
        }
        db.UPDATE(book);
    }

    public List<Book> getLendBooks(){
        return db.getLend(selectReader);
    }

    public String doLend(){
        Reader reader = db.getReader(name,surname);
        Lend lend = new Lend();
        lend.setBook(book);
        lend.setReader(reader);
        db.addLend(lend);
        return "index";
    }

    public String setLend(Book book){
        book.getCatalog().setQuantity(book.getCatalog().getQuantity() - 1);
        if(book.getCatalog().getQuantity() == 0) book.getCatalog().setAvailability(false);
        db.UPDATE(book);
        this.book = book;
        return "getReader4Lend";
    }

    public List<Reader> getReaders(){
        return db.getReaders();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Book getBook() {
        return book;
    }

    public void setSelectReader(Reader selectReader) {
        this.selectReader = selectReader;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean isRender() {
        return render;
    }

    public Reader getSelectReader() {
        return selectReader;
    }
}
