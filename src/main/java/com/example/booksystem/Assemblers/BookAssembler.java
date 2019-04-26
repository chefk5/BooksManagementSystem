package com.example.booksystem.Assemblers;

import com.example.booksystem.DTOs.BookDTO;
import com.example.booksystem.Entities.Book;
import lombok.Builder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Builder
@Service
public class BookAssembler extends ResourceAssemblerSupport<Book, BookDTO> {

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
