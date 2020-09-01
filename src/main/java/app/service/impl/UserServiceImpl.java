package app.service.impl;

import app.model.User;
import app.service.UserService;
import org.apache.log4j.Logger;

import java.io.Serializable;

public class UserServiceImpl extends BaseServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User findById(Serializable key, boolean lock) {
        try {
            return getUserDAO().findById(key, lock);
        } catch (Exception e) {
            logger.error("failed", e);
            return null;
        }
    }

    @Override
    public User findByEmail(String email) {
        try {
            return getUserDAO().findByEmail(email);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean saveOrUpdate(User entity) {
        try {
            getUserDAO().saveOrUpdate(entity);
            return true;
        } catch (Exception e) {
            logger.error("failed!", e);
            throw e;
        }
    }

    @Override
    public boolean delete(User entity) {
        try {
            getUserDAO().delete(entity);
            return true;
        } catch (Exception e) {
            logger.error("failed", e);
            throw e;
        }
    }

}
