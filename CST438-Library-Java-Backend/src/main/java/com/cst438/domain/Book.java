package com.cst438.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long bookId;

    private String title;
    private String author;
    private String checkoutDate;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    
    @ManyToOne
    @JoinColumn(name = "checkout_patron_id")
    private Patron checkoutPatron;

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    
    public Patron getCheckoutPatron() {
        return checkoutPatron;
    }

    public void setCheckoutPatron(Patron checkoutPatron) {
        this.checkoutPatron = checkoutPatron;
    }
}
