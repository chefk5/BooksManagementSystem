package com.example.booksystem.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.SneakyThrows;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;


    private String author;

    @Column(unique = true)
    private String ISBN;

    @OneToMany(mappedBy = "book")
    private List<Comment> comments;

}
