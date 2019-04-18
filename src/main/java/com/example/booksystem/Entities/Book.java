package com.example.booksystem.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;

    private String author;

    private String ISBN;

    @OneToMany
    private List<Comment> comments;

}
