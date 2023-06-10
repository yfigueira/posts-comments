package org.example.comments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "Comment", urlPatterns = {"/api/comments/*"})
public class CommentServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(CommentServlet.class);

    private final ObjectMapper mapper;

    private final CommentService service;

    public CommentServlet() {
        this(JsonMapper.builder()
                .findAndAddModules()
                .build(), new CommentService());
    }

    CommentServlet(ObjectMapper mapper, CommentService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedId = req.getPathInfo().substring(1);
        logger.info("Update On Comment " + requestedId + " Requested At " + LocalDateTime.now());

        try {
            Comment comment = mapper.readValue(req.getInputStream(), Comment.class);
            mapper.writeValue(resp.getOutputStream(), service.update(comment));
        } catch (UnauthorizedCommentException | InvalidFormatException ex) {
            logger.warn("Bad Request - Unauthorized Comment Format");
            mapper.writeValue(resp.getOutputStream(), null);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestedId = req.getPathInfo().substring(1);
        logger.info("Delete On Comment " + requestedId + " Requested At " + LocalDateTime.now());

        try {
            Comment comment = mapper.readValue(req.getInputStream(), Comment.class);
            mapper.writeValue(resp.getOutputStream(), service.delete(comment));
        } catch (InvalidFormatException e) {
            logger.warn("Bad Request - Unauthorized Id Format");
            mapper.writeValue(resp.getOutputStream(), -1);
        }
    }
}
