package app.service.impl;

import app.info.BookingRequestInfo;
import app.model.BookingRequest;
import app.service.BookingRequestService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class BookingRequestServiceImpl extends BaseServiceImpl implements BookingRequestService {
    private static final Logger logger = Logger.getLogger(BookingRequestServiceImpl.class);

    @Override
    public BookingRequest findBookingRequest(int id) {
        try {
            return getBookingRequestDAO().findById(id, false);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean createBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException {
        try {
            logger.info("Creating new booking request");
            getBookingRequestDAO().saveOrUpdate(bookingRequestInfo.toBookingRequest());
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean updateBookingRequest(BookingRequestInfo bookingRequestInfo) throws InvocationTargetException, IllegalAccessException {
        String[] ignoreProperties = {"id"};

        try {
            BookingRequest fieldType = getBookingRequestDAO().findById(bookingRequestInfo.getId(), true);
            BeanUtils.copyProperties(bookingRequestInfo.toBookingRequest(), fieldType, ignoreProperties);
            getBookingRequestDAO().saveOrUpdate(fieldType);
            logger.info(String.format("Updated Field Type having id: %d", bookingRequestInfo.getId()));
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public boolean deleteBookingRequest(int id) {
        try {
            BookingRequest bookingRequest = getBookingRequestDAO().findById(id, true);
            bookingRequest.setStatus(BookingRequest.Status.CANCELED);
            getBookingRequestDAO().saveOrUpdate(bookingRequest);
            logger.info(String.format("Delete Field Type having id: %d", id));
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public List<BookingRequest> loadBookingRequests() {
        try {
            logger.info("Get Booking Requests list");
            return getBookingRequestDAO().loadBookingRequests();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<BookingRequest> findByPeriod(BookingRequestInfo bookingRequestInfo) {
        try {
            logger.info("Get Booking Request In Period");
            return getBookingRequestDAO().findByPeriod(bookingRequestInfo);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
