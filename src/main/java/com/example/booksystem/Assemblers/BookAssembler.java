package com.example.booksystem.Assemblers;

import com.example.booksystem.DTOs.BookDTO;
import com.example.booksystem.Entities.Book;
import com.example.booksystem.Entities.Comment;
import com.example.booksystem.Repositories.CommentRepo;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookAssembler extends ResourceAssemblerSupport<Book, BookDTO> {


CommentRepo commentRepo;

    public BookAssembler() {
        super(Book.class, BookDTO.class);
    }


    @Override
    public BookDTO toResource(Book book) {
        return BookDTO.builder()
                ._id(book.getId())
                .title(book.getTitle())
                .ISBN(book.getISBN())
                .author(book.getAuthor())
                .build();
    }


}
