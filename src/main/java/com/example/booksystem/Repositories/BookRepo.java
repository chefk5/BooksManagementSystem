package com.example.booksystem.Repositories;

import com.example.booksystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepo extends JpaRepository<Book, UUID> {
}
