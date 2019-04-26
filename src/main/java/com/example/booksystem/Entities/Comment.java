package com.example.booksystem.Entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    private Book book;

    @CreationTimestamp
    private LocalDateTime timeCreated;

}
