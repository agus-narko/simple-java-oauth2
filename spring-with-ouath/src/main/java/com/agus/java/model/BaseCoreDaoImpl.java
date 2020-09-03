package com.agus.java.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Basic DAO operations dependent with Hibernate's specific classes
 * @see SessionFactory
 */
@SuppressWarnings("unchecked")
@Repository
@Transactional
public abstract class BaseCoreDaoImpl<E, K extends Serializable> implements BaseCoreDao<E, K> {
    @Autowired
    private SessionFactory sessionFactory;

    protected Class<? extends E> daoType;

    Session sessionCreateQuery;

    Transaction txn = null;

    /**
     * By defining this class as abstract, we prevent Spring from creating instance of this class
     * If not defined abstract getClass().getGenericSuperClass() would return Object.
     * There would be exception because Object class does not hava constructor with parameters.
     */
    public BaseCoreDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        daoType = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }


    @Override
    public void saveOrUpdate(E entity) {
        currentSession().saveOrUpdate(entity);
    }


    @Override
    public void update(E entity) {
        currentSession().saveOrUpdate(entity);
    }


    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }


    @Override
    public E find(K key) {
        return currentSession().get(daoType, key);
    }


    @Override
    public List<E> getAll() {
        return currentSession().createCriteria(daoType).list();
    }

    @Override
    public Query createQuery(String sqlBuilder) {
        sessionCreateQuery = sessionFactory.openSession();
        sessionCreateQuery.setFlushMode(FlushMode.MANUAL);
        txn = sessionCreateQuery.getSession().beginTransaction();
        Query query = sessionCreateQuery.createQuery(sqlBuilder);
        return query;
    }

    @Override
    public Query createSQLQuery(String sqlBuilder) {
        sessionCreateQuery = sessionFactory.openSession();
        sessionCreateQuery.setFlushMode(FlushMode.MANUAL);
        txn = sessionCreateQuery.getSession().beginTransaction();
        Query query = sessionCreateQuery.createSQLQuery(sqlBuilder);
        return query;
    }

    @Override
    public Session getCurrentSession() {
        Session session = currentSession();
        session.setFlushMode(FlushMode.MANUAL);
        return session;
    }

    @Override
    public Session openSession() {
        Session session = sessionFactory.openSession();
        session.setFlushMode(FlushMode.MANUAL);
        return session;
    }

    @Override
    public void closeSessionCreateQuery() {
        txn.commit();
        sessionCreateQuery.close();
    }

    @Override
    public void closeSession() {
        txn.commit();
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}