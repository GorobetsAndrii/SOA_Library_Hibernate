package app.client;


import app.dbObjects.Book;
import app.interfaces.IDB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;

@ManagedBean(name = "Lib")
@SessionScoped
public class Library {
    @EJB
    IDB db;

    private Book bookToUpdate;

    public Library(){
    }

    public List<Book> getAll(){
        return db.SELECT_ALL();
    }

    public void delete(Book book){
        db.DELETE(book);
    }

    public String update(){
        db.UPDATE(bookToUpdate);
        return "index";
    }

    public String openUpdate(Book book){
        bookToUpdate = book;
        return "updateBook";
    }

    public boolean isReserved(Book book){
        return db.checkReserved(book);
    }

    public String openReaders(){
        return "readers";
    }

    public String openAdd(){
        return "addNewBook";
    }

    public void setBookToUpdate(Book bookToUpdate) {
        this.bookToUpdate = bookToUpdate;
    }

    public Book getBookToUpdate() {
        return bookToUpdate;
    }
}
