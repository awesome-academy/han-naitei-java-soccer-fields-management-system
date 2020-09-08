package app.controller;

import app.info.BookingRequestInfo;
import app.info.FieldInfo;
import app.model.BookingRequest;
import app.model.Field;
import app.model.User;
import app.service.BookingRequestService;
import app.service.FieldService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookingRequestController extends BaseController {
    private static final Logger logger = Logger.getLogger(BookingRequestController.class);

    private final BookingRequestService bookingRequestService;
    private final UserService userService;
    private final FieldService fieldService;

    @Autowired
    public BookingRequestController(BookingRequestService bookingRequestService, UserService userService, FieldService fieldService) {
        this.bookingRequestService = bookingRequestService;
        this.userService = userService;
        this.fieldService = fieldService;
    }

    @GetMapping(path = "/booking-requests")
    public ModelAndView index(@RequestParam(value = "search", required = false) String search) {
        logger.info("Index");

        ModelAndView model = new ModelAndView("views/booking-requests/index");
        String title = "Booking requests List";
        model.addObject("title", title);
//        if (search == null) {
        model.addObject("data", bookingRequestService.loadBookingRequests());
//        } else {
//            model.addObject("data", fieldService.searchFields(search));
//        }

        return model;
    }

    @GetMapping(path = "/fields/{id}/booking-requests/new")
    public String createBookingView(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
        logger.info("Create Booking");
        FieldInfo fieldInfo = fieldService.findField(id);
        if (fieldInfo == null)
            return handleRedirect(redirectAttributes, "error", "Field not found.", "/fields");


        BookingRequestInfo bookingRequestInfo = new BookingRequestInfo();
        bookingRequestInfo.setField(fieldInfo.toField());

        String title = "Create Booking Request for Field: " + fieldInfo.getName();
        model.addAttribute("title", title);
        model.addAttribute("createBookingForm", bookingRequestInfo);

        return "views/booking-requests/create";
    }

    @PostMapping(path = "/booking-requests")
    public String create(BookingRequestInfo bookingRequestInfo, final RedirectAttributes redirectAttributes, Authentication authentication) {
        logger.info("POST BOOKING REQUESTS");
        User user = userService.findByEmail(authentication.getName());
        if (user == null) {
            return handleRedirect(redirectAttributes, "error", "User not logged in", "login");
        }
        List<BookingRequest> existedBookingRequest = bookingRequestService.findByPeriod(bookingRequestInfo);

        if (existedBookingRequest.size() != 0) {
            logger.info(existedBookingRequest.size());
            return handleRedirect(redirectAttributes, "error", "Invalid booking request", "fields/" + bookingRequestInfo.getField().getId() + "/create-booking");
        }
        try {
            bookingRequestInfo.setUser(user);
            bookingRequestService.createBookingRequest(bookingRequestInfo);
            return handleRedirect(redirectAttributes, "success", "Booking request created.", "/booking-requests");
        } catch (Exception e) {
            return handleRedirect(redirectAttributes, "error", "Invalid booking request", "fields/" + bookingRequestInfo.getField().getId() + "/create-booking");
        }
    }
}
