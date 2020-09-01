package app.dao;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class GenericDAO<PK extends Serializable, T> extends HibernateDaoSupport {

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> persistentClass;

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @SuppressWarnings("unchecked")
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[1];
    }

    public T findById(Serializable key) {
        return findById(key, true);
    }

    public T findById(Serializable key, boolean lock) {
        if (lock) {
            return getSession().load(getPersistentClass(), key, LockMode.PESSIMISTIC_READ);
        } else {
            return getSession().get(getPersistentClass(), key);
        }
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    public T saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
