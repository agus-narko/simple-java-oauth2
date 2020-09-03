package com.agus.java.model;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *  https://vrtoonjava.wordpress.com/2012/06/17/part-3-dao-and-service-layer/
 * @param <E>
 * @param <K>
 */
public interface BaseCoreDao<E,K> {
    public void add(E entity) ;
    public void saveOrUpdate(E entity) ;
    public void update(E entity) ;
    public void remove(E entity);
    public E find(K key);
    public List<E> getAll() ;
    public Query createQuery(String sqlBuilder);
    public Query createSQLQuery(String sqlBuilder);
    public Session getCurrentSession();
    public Session openSession();
    public void closeSession();
    public void closeSessionCreateQuery();
}