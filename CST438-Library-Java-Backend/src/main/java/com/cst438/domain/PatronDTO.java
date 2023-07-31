package com.cst438.domain;

import java.util.List;

public class PatronDTO {
    private Long patronId;
    private String name;
    private List<BookDTO> books;

    public PatronDTO() {
    }

    public PatronDTO(Long patronId, String name, List<BookDTO> books) {
        this.patronId = patronId;
        this.name = name;
        this.books = books;
    }

    // Getters and Setters

    public Long getPatronId() {
        return patronId;
    }

    public void setPatronId(Long patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}