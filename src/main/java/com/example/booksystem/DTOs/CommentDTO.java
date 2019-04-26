package com.example.booksystem.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentDTO extends ResourceSupport {

    Long _id;
    @NotNull
    String content;
    @CreationTimestamp
    LocalDateTime timeCreated;


}
