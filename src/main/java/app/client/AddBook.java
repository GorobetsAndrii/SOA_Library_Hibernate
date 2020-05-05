package app.client;

import app.dbObjects.Author;
import app.dbObjects.Book;
import app.dbObjects.Catalog;
import app.dbObjects.Category;
import app.interfaces.IDB;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "addition")
@ViewScoped
public class AddBook {
    private String title;
    private String name;
    private String surname;
    private String isbn;
    private String category;
    private int quantity;

    @EJB
    IDB db;

    public AddBook(){

    }

    public String add(){
        Author a = db.getAuthor(name,surname);
        Category c = db.getCategory(category);
        Book b = new Book(title,isbn);
        b.setCategory(c);
        b.setAuthor(a);
        db.setCatalog(quantity,b);

        return "index";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }
}
