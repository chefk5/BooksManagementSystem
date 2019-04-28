package com.example.booksystem;

import com.example.booksystem.Controllers.BookController;
import com.example.booksystem.DTOs.BookDTO;
import com.example.booksystem.DTOs.CommentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BooksystemApplication.class)
@WebAppConfiguration
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    BookController bookController;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

    }

    @Test
    public void shouldAddBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Javas")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        mockMvc.perform(post("/BMS/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void shouldNotAddSameBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Javas")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        bookController.postBooks(bookDTO);

        mockMvc.perform(post("/BMS/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void shouldNotAddBookWithInvalidISBN() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Javas")
                .author("Kaur Kala")
                .ISBN("978-1-")
                .build();

        mockMvc.perform(post("/BMS/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isBadRequest())
                .andReturn();
    }


    @Test
    public void shouldGetBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Java")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        bookController.postBooks(bookDTO);
        mockMvc.perform(get("/BMS/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Uus Java"))
                .andReturn();

    }

    @Test
    public void shouldReturn404WhenBookNotFound() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Java")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        mockMvc.perform(get("/BMS/books/8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    public void shouldGetBooks() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Java")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        bookController.postBooks(bookDTO);
        mockMvc.perform(get("/BMS/books/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

    }

    @Test
    public void shouldUpdateBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Java")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        bookController.postBooks(bookDTO);

        BookDTO bookDTO2 = BookDTO.builder()
                .title("Uus Java 22")
                .author("Kaur Kala 22")
                .ISBN("978-1-60309-077-3")
                .build();

        mockMvc.perform(patch("/BMS/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Uus Java 22"))
                .andReturn();

    }

    @Test
    public void shouldDeleteBook() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Java")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        bookController.postBooks(bookDTO);

        mockMvc.perform(delete("/BMS/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void shouldAddComment() throws Exception {
        BookDTO bookDTO = BookDTO.builder()
                .title("Uus Javas")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        bookController.postBooks(bookDTO);

        CommentDTO commentDTO = CommentDTO.builder()
                .content("nice")
                .build();

        mockMvc.perform(post("/BMS/books/1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.content").value("nice"))
                .andReturn();

    }

    @Test
    public void shouldGetComment() throws Exception {
        Long id = 39832L;
        BookDTO bookDTO = BookDTO.builder()
                ._id(id)
                .title("Uus Javas")
                .author("Kaur Kala")
                .ISBN("978-1-60309-077-3")
                .build();

        bookController.postBooks(bookDTO);

        CommentDTO commentDTO = CommentDTO.builder()
                ._id(id)
                .content("nice")
                .build();

        bookController.addComment(1L, commentDTO);

        mockMvc.perform(get("/BMS/books/1/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(commentDTO)))
                .andExpect(status().isOk())
                .andReturn();
    }



}
