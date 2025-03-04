package org.example.safargulov.projecthibernate2.repository;

import lombok.Getter;
import org.example.safargulov.projecthibernate2.config.SessionCreator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractRepository<E, ID extends Serializable> implements BaseRepository<E, ID> {
    protected final SessionCreator creator;
    @Getter
    private final Class<E> entityClass;

    protected AbstractRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
        this.creator = new SessionCreator();
    }

    @Override
    public Optional<E> find(ID id) {
        try (Session session = creator.getSession()) {
            E entity = session.find(entityClass, id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find entity by id: " + id, e);
        }
    }

    @Override
    public Stream<E> findAll() {
        try (Session session = creator.getSession()) {
            String hql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            Query<E> query = session.createQuery(hql, entityClass);
            return query.getResultStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        Transaction transaction = null;
        try (Session session = creator.getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save entity", e);
        }
    }

    @Override
    public void delete(E t) {
        if (t == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        Transaction transaction = null;
        try (Session session = creator.getSession()) {
            transaction = session.beginTransaction();
            session.remove(t);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<E> update(E t) {
        if (t == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        Transaction transaction = null;
        try (Session session = creator.getSession()) {
            transaction = session.beginTransaction();
            E updated = session.merge(t);
            transaction.commit();
            return Optional.ofNullable(updated);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty();

    }
}
