package app.service.impl;

import app.dao.FieldTypeDAO;
import app.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FieldTypeDAO fieldTypeDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public FieldTypeDAO getFieldTypeDAO() {
        return fieldTypeDAO;
    }

    public void setFieldTypeDAO(FieldTypeDAO fieldTypeDAO) {
        this.fieldTypeDAO = fieldTypeDAO;
    }
}
