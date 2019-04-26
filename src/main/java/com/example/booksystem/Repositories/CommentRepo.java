package com.example.booksystem.Repositories;

import com.example.booksystem.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

}
