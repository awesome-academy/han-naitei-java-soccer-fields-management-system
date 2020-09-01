package app.dao;

import app.model.FieldType;

import java.util.List;

public interface FieldTypeDAO extends BaseDAO<Integer, FieldType> {

    FieldType findByName(String name);

    List<FieldType> loadFieldTypes();
}
