package app.interfaces;

import app.dbObjects.*;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

@Remote
public interface IDB extends Serializable {
    public void INSERT(Book book);
    public void DELETE(Book book);
    public void UPDATE(Book book);
    public void addLend(Lend lend);
    public void UPDATECategory(Category category);
    public List<Book> SELECT_ALL();
    public Author getAuthor(String name, String surname);
    public Category getCategory(String type);
    public void setCatalog(int quantity,Book b);
    public Reader getReader(String name,String surname);
    public List<Reader> getReaders();
    public List<Book> getLend(Reader reader);
    public void returnBook(Book book,Reader reader);
    public boolean checkReserved(Book book);
}
