package app.interfaces;

import app.dbObjects.Author;
import app.dbObjects.Book;
import app.dbObjects.Reader;

import javax.ejb.Remote;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Remote
public interface IDBCustomQuery extends Serializable {
    public List<Author> getAuthors();
    public Author getAuthor4Id(long id);
    public Book getBook4Id(long id);
    public Reader getReader4Id(long id);
    public List<Reader> firstQuery(long authorId, Timestamp from, Timestamp to);
    public List<Reader> secondQuery(long bookId, Timestamp fromTime, Timestamp toTime);
    public List<Author> thirdQuery(long readerId);
    public List<Author> fourthQuery(boolean most);
}
