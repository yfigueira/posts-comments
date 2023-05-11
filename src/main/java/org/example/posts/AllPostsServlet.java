package org.example.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AllPosts", urlPatterns = {"/api/posts"})
public class AllPostsServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(AllPostsServlet.class);

    private final ObjectMapper mapper;

    private final PostService service;

    public AllPostsServlet() {
        this(new ObjectMapper(), new PostService());
    }

    AllPostsServlet(ObjectMapper mapper, PostService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request For Posts Received At: " + LocalDateTime.now());
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), service.findAll());
    }
}
