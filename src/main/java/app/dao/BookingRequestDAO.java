package app.dao;

import app.info.BookingRequestInfo;
import app.model.BookingRequest;

import java.util.List;

public interface BookingRequestDAO extends BaseDAO<Integer, BookingRequest> {

    List<BookingRequest> loadBookingRequests();

    List<BookingRequest> findByPeriod(BookingRequestInfo bookingRequestInfo);
}
