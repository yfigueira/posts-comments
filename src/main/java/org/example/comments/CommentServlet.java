package org.example.comments;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "Comment", urlPatterns = {"/api/comments/*"})
public class CommentServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(CommentServlet.class);

    private final ObjectMapper mapper;

    private final CommentService service;

    CommentServlet() {
        this(new ObjectMapper(), new CommentService());
    }

    CommentServlet(ObjectMapper mapper, CommentService service) {
        this.mapper = mapper;
        this.service = service;
    }
}
