package app.service;

import java.io.Serializable;

public interface BaseService<PK, T> {
    T findById(Serializable key, boolean lock);

    boolean saveOrUpdate(T entity);

    boolean delete(T entity);
}
