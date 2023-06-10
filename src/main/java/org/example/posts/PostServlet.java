package org.example.posts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.comments.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "Post", urlPatterns = {"/api/posts/*"})
public class PostServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(PostServlet.class);

    private final PostService service;

    private final ObjectMapper mapper;

    public PostServlet() {
        this(new PostService(), JsonMapper.builder()
                .findAndAddModules()
                .build());
    }

    PostServlet(PostService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedId = req.getPathInfo().substring(1);
        logger.info("Request For Post " + requestedId + " Received At " + LocalDateTime.now());

        try {
            Integer postId = Integer.valueOf(requestedId);
            Post responsePost = service.findById(postId);

            resp.setStatus(
                    responsePost.equals(PostService.fallbackPost())
                            ? HttpServletResponse.SC_NOT_FOUND
                            : HttpServletResponse.SC_OK
            );
            resp.setContentType("application/json;charset=UTF-8");

            mapper.writeValue(resp.getOutputStream(), responsePost);
        } catch (NumberFormatException e) {
            logger.warn("Bad Request - Unauthorized Id Format");

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getOutputStream(), PostService.fallbackPost());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedId = req.getPathInfo().substring(1);
        logger.info("Update On Post " + requestedId + " Requested At " + LocalDateTime.now());


        try {
            Post postToUpdate = mapper.readValue(req.getInputStream(), Post.class);
            Post responsePost = service.update(postToUpdate);

            resp.setStatus(
                    responsePost.equals(PostService.fallbackPost())
                            ? HttpServletResponse.SC_NOT_FOUND
                            : HttpServletResponse.SC_OK
            );
            resp.setContentType("application/json;charset=UTF-8");

            mapper.writeValue(resp.getOutputStream(), responsePost);
        } catch (InvalidFormatException e) {
            logger.warn("Bad Request - Unauthorized Id Format");

            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            mapper.writeValue(resp.getOutputStream(), PostService.fallbackPost());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String postId = req.getPathInfo().substring(1);
        logger.info("New Comment On Post " + postId + " Added At " + LocalDateTime.now());

        Comment newComment = mapper.readValue(req.getInputStream(), Comment.class);
        mapper.writeValue(resp.getOutputStream(), service.addComment(newComment));
    }
}
