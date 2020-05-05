package app.client;

import app.dbObjects.Author;
import app.dbObjects.Book;
import app.dbObjects.Reader;
import app.interfaces.IDB;
import app.interfaces.IDBCustomQuery;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean(name = "criteriaManager")
@SessionScoped
public class CustomCriteriaManager {

    private long authorId;
    private Date from;
    private Date to;
    private long bookId;
    private long readerId;
    private boolean most;
    private boolean first;
    private boolean second;
    private boolean third;
    private boolean fourth;

    @EJB
    IDBCustomQuery dbCustomQuery;

    @EJB
    IDB db;

    public CustomCriteriaManager(){

    }

    public List<Reader> getFirstResult(){
        return dbCustomQuery.firstQuery(authorId, new Timestamp(from.getTime()), new Timestamp(to.getTime()));
    }

    public List<Reader> getSecondResult(){
        return dbCustomQuery.secondQuery(bookId, new Timestamp(from.getTime()), new Timestamp(to.getTime()));
    }

    public List<Author> getThirdResult(){
        return dbCustomQuery.thirdQuery(readerId);
    }

    public List<Author> getFourthResult(){
        return dbCustomQuery.fourthQuery(most);
    }

    public String openCustomResult(int query){
        first = false;
        second = false;
        third = false;
        fourth = false;

        switch (query){
            case 1 :
                first = true;
                break;
            case 2:
                second = true;
                break;
            case 3:
                third = true;
                break;
            case 4:
                fourth = true;
                break;
        }
        return "customQueries";
    }

    public List<Book> getBooks(){
        return db.SELECT_ALL();
    }

    public List<Reader> getReaders(){
        return db.getReaders();
    }

    public List<Author> getAuthors(){
        return dbCustomQuery.getAuthors();
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public long getBookId() {
        return bookId;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isFirst() {
        return first;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }

    public void setThird(boolean third) {
        this.third = third;
    }

    public void setFourth(boolean fourth) {
        this.fourth = fourth;
    }

    public void setMost(boolean most) {
        this.most = most;
    }

    public boolean isMost() {
        return most;
    }

    public boolean isSecond() {
        return second;
    }

    public boolean isThird() {
        return third;
    }

    public boolean isFourth() {
        return fourth;
    }
}
