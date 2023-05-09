package org.example.posts;

import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

class PostH2DBRepository implements PostRepository{

    @Override
    public List<Post> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Post> result = session.createQuery("from Post", Post.class).list();

        transaction.commit();
        session.close();

        System.out.println(result);

        return result;
    }

    @Override
    public Optional<Post> findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post result = session.get(Post.class, id);

        transaction.commit();
        session.close();

        System.out.println(result);

        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Post> update(Post post) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Post result = session.get(Post.class, post.getId());

        if (result != null) result.setDescription(post.getDescription());

        transaction.commit();
        session.close();

        return Optional.ofNullable(result);
    }
}
