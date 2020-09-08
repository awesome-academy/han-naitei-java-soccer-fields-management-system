package app.service.impl;

import app.info.FieldInfo;
import app.info.ReviewInfo;
import app.model.Review;
import app.model.User;
import app.service.ReviewService;
import app.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl extends BaseServiceImpl implements ReviewService {
    private static final Logger logger = Logger.getLogger(ReviewServiceImpl.class);

    private static UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        ReviewServiceImpl.userService = userService;
    }

    @Override
    public ReviewInfo findReview(int id) {
        try {
            return new ReviewInfo(getReviewDAO().findById(id, false));
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<ReviewInfo> loadReviews(FieldInfo fieldInfo) {
        try {
            List<Review> reviews = getReviewDAO().loadReviews(fieldInfo.toField());
            logger.info("Got review list");
            return reviews.stream().map(ReviewInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<ReviewInfo> loadReviews(FieldInfo fieldInfo, List<Integer> userIds) {
        try {
            List<Review> reviews = getReviewDAO().loadReviews(fieldInfo.toField(), userIds);
            logger.info("Got review list");
            return reviews.stream().map(ReviewInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public ReviewInfo loadCurrentUsersReview(FieldInfo fieldInfo) {
        try {
            User currentUser = userService.getCurrentUser();

            if (currentUser == null)
                return null;

            List<Review> reviews = getReviewDAO().loadReviews(fieldInfo.toField(), Arrays.asList(currentUser.getId()));

            if (reviews.size() == 0)
                return null;

            logger.info("Got current user's review");
            return new ReviewInfo(reviews.get(0));
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<ReviewInfo> loadOtherUsersReviews(FieldInfo fieldInfo) {
        try {
            User currentUser = userService.getCurrentUser();

            List<Review> reviews = currentUser != null ?
                    getReviewDAO().loadReviewsExcept(fieldInfo.toField(), Arrays.asList(currentUser.getId()))
                    :
                    getReviewDAO().loadReviews(fieldInfo.toField());

            if (reviews.size() == 0)
                return null;

            logger.info("Got other user's review");
            return reviews.stream().map(ReviewInfo::new).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }
}
