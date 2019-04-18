package com.example.booksystem.Entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Book book;

    @CreationTimestamp
    private LocalDateTime timeCreated;

}
