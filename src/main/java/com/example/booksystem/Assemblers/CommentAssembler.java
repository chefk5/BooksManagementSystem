package com.example.booksystem.Assemblers;

import com.example.booksystem.DTOs.CommentDTO;
import com.example.booksystem.Entities.Comment;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;

@Service
public class CommentAssembler extends ResourceAssemblerSupport<Comment, CommentDTO> {

    public CommentAssembler() {
        super(Comment.class, CommentDTO.class);
    }


    @Override
    public CommentDTO toResource(Comment comment) {
        return CommentDTO.builder()
                ._id(comment.getId())
                .content(comment.getContent())
                .timeCreated(comment.getTimeCreated())
                .build();
    }
}
