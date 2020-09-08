package app.dao.impl;

import app.dao.BookingRequestDAO;
import app.dao.GenericDAO;
import app.info.BookingRequestInfo;
import app.model.BookingRequest;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class BookingRequestDAOImpl extends GenericDAO<Integer, BookingRequest> implements BookingRequestDAO {

    public BookingRequestDAOImpl() {
        super(BookingRequest.class);
    }

    public BookingRequestDAOImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public List<BookingRequest> loadBookingRequests() {
        return getSession().createQuery("FROM BookingRequest").getResultList();
    }

    @Override
    public List<BookingRequest> findByPeriod(BookingRequestInfo bookingRequestInfo) {
        Query query = getSession().createQuery("FROM BookingRequest br WHERE br.field.id = :field_id" +
                " AND status != 'CANCELED' " +
                "AND ((br.fromDate BETWEEN :fromDate AND :toDate) OR" +
                "(br.toDate BETWEEN :fromDate AND :toDate))");
        query.setParameter("field_id", bookingRequestInfo.getField().getId());
        query.setParameter("fromDate", bookingRequestInfo.getFromDate());
        query.setParameter("toDate", bookingRequestInfo.getToDate());
        return query.getResultList();
    }
}
