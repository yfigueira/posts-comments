package org.example.comments;

import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class CommentH2DBRepository  implements  CommentRepository{

    @Override
    public Optional<Comment> update(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Comment result = session.get(Comment.class, comment.getId());

        if (comment.getContent() != null) result.setContent(comment.getContent());

        transaction.commit();
        session.close();

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Integer> delete(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Integer deletedCommentId = comment.getId();
        session.remove(comment);

        transaction.commit();
        session.close();

        return Optional.ofNullable(deletedCommentId);
    }
}
