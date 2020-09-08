package app.info;

import app.model.BookingRequest;
import app.model.Field;
import app.model.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class BookingRequestInfo {

    private Integer id;
    private Date toDate;
    private Date fromDate;
    private Field field;
    private User user;

    public BookingRequestInfo() {
    }

    public BookingRequestInfo(Integer id, Date toDate, Date fromDate, Field field, User user) {
        this.id = id;
        this.toDate = toDate;
        this.fromDate = fromDate;
        this.field = field;
        this.user = user;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BookingRequest toBookingRequest() throws InvocationTargetException, IllegalAccessException {
        BookingRequest bookingRequest = new BookingRequest();
        BeanUtils.copyProperties(bookingRequest, this);
        return bookingRequest;
    }

    @Override
    public String toString() {
        return "BookingRequestInfo{" +
                "id=" + id +
                ", toDate=" + toDate +
                ", fromDate=" + fromDate +
                ", field_id=" + field.getId() +
                '}';
    }
}
