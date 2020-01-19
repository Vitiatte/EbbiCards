package com.ebbi.EbbiCards.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractHibernateRepository<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T findOne(long id) {
        return getSession().get(clazz, id);
    }

    public List<T> findAll() {
        return getSession().createQuery("from " + clazz.getName()).list();
    }

    public void save(T entity) {
        getSession().persist(entity);
    }

    public T update(T entity) {
        return (T) getSession().merge(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public void deleteById(long id) {
        T entity = findOne(id);
        getSession().delete(entity);
    }

    private final Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
