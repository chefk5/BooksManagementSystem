package com.example.booksystem.Repositories;

import com.example.booksystem.DTOs.BookDTO;
import com.example.booksystem.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepo extends JpaRepository<Book, Long> {


    @Query(value = "SELECT * FROM Book where ISBN = ?1 ", nativeQuery = true)
    Book similarBook(String ISBN);

    @Query(value = "SELECT * FROM Book where id = ?1 ", nativeQuery = true)
    Book similarBookwithId(Long id);





}
