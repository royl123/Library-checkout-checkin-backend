package com.cst438.domain;

public class BookDTO {
    private Long bookId;
    private String title;
    private String author;
    private String checkoutDate;

    public BookDTO() {
    }

    public BookDTO(Long bookId, String title, String author, String checkoutDate) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.checkoutDate = checkoutDate;
    }

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
}