package com.example.booksystem.Controllers;

import com.example.booksystem.DTOs.BookDTO;
import com.example.booksystem.DTOs.CommentDTO;
import com.example.booksystem.Entities.Book;
import com.example.booksystem.Repositories.BookRepo;
import com.example.booksystem.Services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("BMS/")
@Api(value = "BooksControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BookService bookService;


    @GetMapping("/books")
    @ApiOperation("Gets all books")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Book.class)})
    public List<Book> getBooks() {
        return bookService.listBooks();
    }

    @PostMapping("/books/add")
    public ResponseEntity<?> postBooks(@Valid @RequestBody BookDTO bookDTO) {
        return bookService.addBook(bookDTO);
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable(value = "id") Long id, @RequestBody BookDTO bookDTO) {
        bookDTO.set_id(id);
        return bookService.updateBook(bookDTO);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBook(@PathVariable(value = "id") Long id) {
        return bookService.getBook(id);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) {
        return bookService.deleteBook(id);
    }

    @PostMapping("/books/{id}/comment")
    public ResponseEntity<?> addComment(@PathVariable(value = "id") Long id, @RequestBody CommentDTO commentDTO) {
        return bookService.addComment(id, commentDTO);
    }

    @GetMapping("/books/{id}/comment")
    public List<CommentDTO> getComments(@PathVariable(value = "id") Long id) {
        return bookService.getComments(id);
    }
}
