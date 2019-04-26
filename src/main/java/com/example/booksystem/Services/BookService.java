package com.example.booksystem.Services;

import com.example.booksystem.Assemblers.BookAssembler;
import com.example.booksystem.Assemblers.CommentAssembler;
import com.example.booksystem.DTOs.BookDTO;
import com.example.booksystem.DTOs.CommentDTO;
import com.example.booksystem.Entities.Book;
import com.example.booksystem.Entities.Comment;
import com.example.booksystem.Repositories.BookRepo;
import com.example.booksystem.Repositories.CommentRepo;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityExistsException;
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

    public List<Book> listBooks() {
        List<Book> books = bookRepo.findAll();
        return books;
    }

    public ResponseEntity<?> addBook(BookDTO bookDTO) {
        Book sameBook = bookRepo.similarBook(bookDTO.getISBN());
        if(sameBook!=null){
            return new ResponseEntity<>("Book already exists!",HttpStatus.BAD_REQUEST);
        }
        if(!validateIsbn13(bookDTO.getISBN())){
            return new ResponseEntity<>("Invalid ISBN",HttpStatus.BAD_REQUEST);
        }
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setISBN(bookDTO.getISBN());
        bookRepo.saveAndFlush(book);
        BookDTO addedBook = bookAssembler.toResource(book);
        return new ResponseEntity<>(addedBook,HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateBook(BookDTO bookDTO) {
        Book book = bookRepo.similarBookwithId(bookDTO.get_id());
        if(book==null){
            return new ResponseEntity<>("Book does not exist", HttpStatus.BAD_REQUEST);
        }
        if(!validateIsbn13(bookDTO.getISBN())){
            return new ResponseEntity<>("Invalid ISBN",HttpStatus.BAD_REQUEST);
        }
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setISBN(bookDTO.getISBN());
        bookRepo.saveAndFlush(book);
        BookDTO updatedBook = bookAssembler.toResource(book);
        return new ResponseEntity<>(updatedBook,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found."));
        BookDTO bookDTO = bookAssembler.toResource(book);
        return new ResponseEntity<>(bookDTO,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> deleteBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found."));
        bookRepo.delete(book);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> addComment(Long id, CommentDTO commentDTO) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
        Comment comment = new Comment();
        comment.setBook(book);
        comment.setContent(commentDTO.getContent());
        commentRepo.saveAndFlush(comment);
        CommentDTO addedComment = commentAssembler.toResource(comment);
        return new ResponseEntity<>(addedComment, HttpStatus.ACCEPTED);

    }

    public List<CommentDTO> getComments(Long id) {
        List<Comment> comments = commentRepo.findAll();
        List<CommentDTO> commentDTOS = commentAssembler.toResources(comments);
        return commentDTOS;

    }

//Extracted from https://www.moreofless.co.uk/validate-isbn-13-java/
    public boolean validateIsbn13( String isbn ) {
        if ( isbn == null ) {
            return false;
        }

        //remove any hyphens
        isbn = isbn.replaceAll( "-", "" );

        //must be a 13 digit ISBN
        if ( isbn.length() != 13 ) {
            return false;
        }

        try {
            int tot = 0;
            for ( int i = 0; i < 12; i++ ) {
                int digit = Integer.parseInt( isbn.substring( i, i + 1 ) );
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            //checksum must be 0-9. If calculated as 10 then = 0
            int checksum = 10 - (tot % 10);
            if ( checksum == 10 ) {
                checksum = 0;
            }

            return checksum == Integer.parseInt( isbn.substring( 12 ) );
        }
        catch ( NumberFormatException nfe ) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }
    }


    }







