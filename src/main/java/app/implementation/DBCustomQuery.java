package app.implementation;

import app.dbObjects.Author;
import app.dbObjects.Book;
import app.dbObjects.Lend;
import app.dbObjects.Reader;
import app.interfaces.IDBCustomQuery;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Stateless
@Remote(IDBCustomQuery.class)
public class DBCustomQuery implements IDBCustomQuery {


    @PersistenceContext(name = "JPA-Zajecia")
    private EntityManager entityManager;

    public DBCustomQuery(){

    }

    public List<Reader> firstQuery(long authorId, Timestamp fromTime, Timestamp toTime){
        Author author = getAuthor4Id(authorId);
        Query q = entityManager.createQuery("FROM Lend l where l.book.author =: a AND l.lendTime > :ft AND l.returnTime < :tt", Lend.class);
        q.setParameter("a",author);
        q.setParameter("ft",fromTime);
        q.setParameter("tt",toTime);
        List<Lend> lends = q.getResultList();
        HashSet<Reader> readers = new HashSet<Reader>();

        for(Lend l: lends){
            readers.add(l.getReader());
        }

        return new ArrayList<Reader>(readers);
    }

    public List<Reader> secondQuery(long bookId, Timestamp fromTime, Timestamp toTime){
        Book book = getBook4Id(bookId);
        Query q = entityManager.createQuery("FROM Lend l where l.book =: b AND l.lendTime > :ft AND l.returnTime < :tt", Lend.class);
        q.setParameter("b",book);
        q.setParameter("ft",fromTime);
        q.setParameter("tt",toTime);
        List<Lend> lends = q.getResultList();
        HashSet<Reader> readers = new HashSet<Reader>();

        for(Lend l: lends){
            readers.add(l.getReader());
        }

        return new ArrayList<Reader>(readers);
    }

    public List<Author> thirdQuery(long readerId){
        Reader reader = getReader4Id(readerId);
        Query q = entityManager.createQuery("FROM Lend l where l.reader =: r", Lend.class);
        q.setParameter("r",reader);
        List<Lend> lends = q.getResultList();
        HashSet<Author> authors = new HashSet<Author>();

        for(Lend l: lends){
            authors.add(l.getBook().getAuthor());
        }

        return new ArrayList<Author>(authors);
    }

    public List<Author> fourthQuery(boolean most){
        Query q = entityManager.createQuery("FROM Lend", Lend.class);
        List<Lend> lends = q.getResultList();
        q = entityManager.createQuery("FROM Author ", Author.class);
        List<Author> authors = q.getResultList();
        Author author = new Author();
        int minCounter = Integer.MAX_VALUE;
        int maxCounter = 0;
        ArrayList<Author> result = new ArrayList<Author>();
        for(Author a : authors){
            int counter = 0;
            for(Lend l : lends){
                if(a.getId() == l.getBook().getAuthor().getId()) ++counter;
            }
            if(most){
                if(counter > maxCounter){
                    maxCounter = counter;
                    author = a;
                }
            }else{
                if(counter == 0){
                    result.add(a);
                }
                if(counter < minCounter){
                    minCounter = counter;
                    author = a;
                }
            }
        }
        if(result.size() == 0) result.add(author);
        return result;
    }


    public List<Author> getAuthors(){
        Query q = entityManager.createQuery("FROM Author ", Author.class);
        List<Author> authors = q.getResultList();

        return authors;
    }

    public Author getAuthor4Id(long id){
        Query q = entityManager.createQuery("FROM Author a where a.id = :i", Author.class);
        q.setParameter("i",id);
        return (Author) q.getSingleResult();
    }

    public Reader getReader4Id(long id){
        Query q = entityManager.createQuery("FROM Reader r where r.id = :i", Reader.class);
        q.setParameter("i",id);
        return (Reader) q.getSingleResult();
    }

    public Book getBook4Id(long id){
        Query q = entityManager.createQuery("FROM Book b where b.id = :i", Book.class);
        q.setParameter("i",id);
        return (Book) q.getSingleResult();
    }
}
