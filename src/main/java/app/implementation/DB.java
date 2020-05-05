package app.implementation;

import app.dbObjects.*;
import app.interfaces.IDB;

import javax.ejb.Lock;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote(IDB.class)
public class DB implements IDB {

    @PersistenceContext(name = "JPA-Zajecia")
    private EntityManager entityManager;

    public DB(){

    }

    @Lock
    @Transactional
    public void INSERT(Book book){
        entityManager.persist(book);
    }

    @Lock
    @Transactional
    public void DELETE(Book book){
        Book b = entityManager.find(Book.class,book.getId());
        entityManager.remove(b);
    }

    @Lock
    @Transactional
    public void addLend(Lend lend){
        entityManager.persist(lend);
    }

    @Lock
    @Transactional
    public void UPDATE(Book book){
        entityManager.merge(book);
    }

    @Lock
    @Transactional
    public void UPDATECategory(Category category){
        entityManager.merge(category);
    }

    @Lock
    @Transactional
    public void returnBook(Book book,Reader reader){
        Query q = entityManager.createQuery("FROM Lend l where l.reader = :r and l.book = :b and l.returnTime = null",Lend.class);
        q.setParameter("r",reader);
        q.setParameter("b",book);
        List<Lend> lends = q.getResultList();

        for(Lend l : lends){
            l.setReturnTime(new Timestamp(System.currentTimeMillis()));
            entityManager.merge(l);
        }
    }

    @Lock
    @Transactional
    public boolean checkReserved(Book book){
        Query q = entityManager.createQuery("FROM Lend l where l.book = :b and l.returnTime = null",Lend.class);
        q.setParameter("b",book);
        List<Lend> lends = q.getResultList();

        return lends.size() > 0 ? true : false;
    }

    @Transactional
    public List<Book> getLend(Reader reader){
        Query q = entityManager.createQuery("FROM Lend l where l.reader = :r and l.returnTime = null ",Lend.class);
        q.setParameter("r",reader);
        List<Lend> lends = q.getResultList();
        List<Book> books = new ArrayList<Book>();

        for(Lend l : lends){
            books.add(l.getBook());
        }

        return books;
    }

    @Transactional
    public List<Book> SELECT_ALL(){
        Query q = entityManager.createQuery("FROM Book", Book.class);
        List<Book> books = q.getResultList();

        return books;
    }

    @Transactional
    public List<Reader> getReaders(){
        Query q = entityManager.createQuery("FROM Reader ", Reader.class);
        List<Reader> readers = q.getResultList();

        return readers;
    }

    @Lock
    @Transactional
    public Author getAuthor(String name, String surname){
        Author author;
        try{
            Query q = entityManager.createQuery("FROM Author a where a.name = :n and a.surname = :s",Author.class);
            q.setParameter("n",name);
            q.setParameter("s",surname);
            author = (Author) q.getSingleResult();
            return author;
        }catch (Exception e){
            author = new Author();
            author.setName(name);
            author.setSurname(surname);
            entityManager.persist(author);
            return author;
        }
    }

    @Lock
    @Transactional
    public Category getCategory(String type){
        Category category;
        try{
            Query q = entityManager.createQuery("FROM Category c where c.type = :t",Category.class);
            q.setParameter("t",type);
            category = (Category) q.getSingleResult();
            return category;
        }catch (Exception e){
            category = new Category(type);
            entityManager.persist(category);
            return category;
        }
    }

    @Lock
    @Transactional
    public void setCatalog(int quantity,Book b){
        Catalog catalog = new Catalog(quantity);
        catalog.setBook(b);
        entityManager.persist(catalog);
    }

    @Lock
    @Transactional
    public Reader getReader(String name, String surname){
        Reader reader;
        try{
            Query q = entityManager.createQuery("FROM Reader r where r.name = :n AND r.surname = :s",Reader.class);
            q.setParameter("n",name);
            q.setParameter("s",surname);
            reader = (Reader) q.getSingleResult();
            return reader;
        }catch (Exception e){
            reader = new Reader(name,surname);
            entityManager.persist(reader);
            return reader;
        }
    }
}
