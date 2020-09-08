package app.service.impl;

import app.dao.BookingRequestDAO;
import app.dao.FieldDAO;
import app.dao.FieldTypeDAO;
import app.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseServiceImpl {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private FieldTypeDAO fieldTypeDAO;

    @Autowired
    private FieldDAO fieldDAO;

    @Autowired
    private BookingRequestDAO bookingRequestDAO;

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

    public FieldDAO getFieldDAO() {
        return fieldDAO;
    }

    public void setFieldDAO(FieldDAO fieldDAO) {
        this.fieldDAO = fieldDAO;
    }

    public BookingRequestDAO getBookingRequestDAO() {
        return bookingRequestDAO;
    }

    public void setBookingRequestDAO(BookingRequestDAO bookingRequestDAO) {
        this.bookingRequestDAO = bookingRequestDAO;
    }
}
