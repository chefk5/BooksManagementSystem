package com.example.booksystem.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO extends ResourceSupport {

    Long _id;
    @NotNull
    String title;
    @NotNull
    String author;
    @NotNull
    String ISBN;
    //private List<Comment> comments;


}
