CREATE TABLE comments(
    id INTEGER PRIMARY KEY auto_increment,
    content VARCHAR,
    date DATE,
    post_id INTEGER,
    FOREIGN KEY (post_id) REFERENCES posts(id)
);