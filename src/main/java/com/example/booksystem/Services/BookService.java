package com.example.booksystem.Services;

import com.example.booksystem.Assemblers.BookAssembler;
import com.example.booksystem.Assemblers.CommentAssembler;
import com.example.booksystem.DTOs.BookDTO;
import com.example.booksystem.DTOs.CommentDTO;
import com.example.booksystem.Entities.Book;
import com.example.booksystem.Entities.Comment;
import com.example.booksystem.Repositories.BookRepo;
import com.example.booksystem.Repositories.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    @Resource
    BookAssembler bookAssembler;

    @Resource
    CommentAssembler commentAssembler;

    @Autowired
    CommentRepo commentRepo;

    public List<BookDTO> listBooks() {
        List<Book> books = bookRepo.findAll();
        List<BookDTO> bookDTOLists = bookAssembler.toResources(books);
        return bookDTOLists;
    }

    public BookDTO addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setISBN(bookDTO.getISBN());
        bookRepo.saveAndFlush(book);
        return bookAssembler.toResource(book);
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        Book book = bookRepo.findById(bookDTO.get_id()).orElseThrow(() -> new EntityNotFoundException("Book not found."));
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setISBN(bookDTO.getISBN());
        bookRepo.saveAndFlush(book);
        return bookDTO;
    }

    public ResponseEntity<?> deleteBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found."));
        bookRepo.delete(book);
        return ResponseEntity.ok().build();
    }

    public CommentDTO addComment(Long id, CommentDTO commentDTO) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found."));
        Comment comment = new Comment();
        comment.setBook(book);
        comment.setContent(commentDTO.getContent());
        commentRepo.saveAndFlush(comment);
        return commentAssembler.toResource(comment);

    }

    public List<CommentDTO> getComments(Long id) {
        List<Comment> comments = commentRepo.findAll();
        List<CommentDTO> commentDTOS = commentAssembler.toResources(comments);
        return commentDTOS;

    }
}






