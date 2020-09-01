package app.dao.impl;

import app.dao.FieldTypeDAO;
import app.dao.GenericDAO;
import app.model.FieldType;
import org.hibernate.SessionFactory;

import java.util.List;

public class FieldTypeDAOImpl extends GenericDAO<Integer, FieldType> implements FieldTypeDAO {

    public FieldTypeDAOImpl() {
        super(FieldType.class);
    }

    public FieldTypeDAOImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public FieldType findByName(String name) {
        return (FieldType) getSession().createQuery("FROM FieldType WHERE name = ?").setParameter(0, name)
                .getSingleResult();
    }

    @Override
    public List<FieldType> loadFieldTypes() {
        return getSession().createQuery("FROM FieldType").getResultList();
    }
}
