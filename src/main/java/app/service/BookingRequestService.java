package app.service;

import app.info.BookingRequestInfo;
import app.info.FieldTypeInfo;
import app.model.BookingRequest;
import app.model.FieldType;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public interface BookingRequestService {

    BookingRequest findBookingRequest(int id);

    boolean createBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException;

    boolean updateBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException;

    boolean deleteBookingRequest(int id);

    List<BookingRequest> findByPeriod(BookingRequestInfo bookingRequestInfo);

    List<BookingRequest> loadBookingRequests();
}
