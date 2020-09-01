package app.service.impl;

import app.model.FieldType;
import app.service.FieldTypeService;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;

public class FieldTypeServiceImpl extends BaseServiceImpl implements FieldTypeService {
    private static final Logger logger = Logger.getLogger(FieldTypeServiceImpl.class);

    @Override
    public boolean saveOrUpdate(FieldType fieldType) {
        try {
            getFieldTypeDAO().saveOrUpdate(fieldType);
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public boolean deleteFieldType(Integer id) {
        try {
            FieldType fieldType = getFieldTypeDAO().findById(id, true);
            return delete(fieldType);
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public FieldType findByName(String name) {
        try {
            return getFieldTypeDAO().findByName(name);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public List<FieldType> loadFieldTypes() {
        try {
            return getFieldTypeDAO().loadFieldTypes();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public FieldType findById(Serializable key, boolean lock) {
        try {
            return getFieldTypeDAO().findById(key, lock);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public boolean delete(FieldType fieldType) {
        try {
            getFieldTypeDAO().delete(fieldType);
            return true;
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
    }
}
