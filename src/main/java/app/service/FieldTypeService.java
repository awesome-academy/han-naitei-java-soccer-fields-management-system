package app.service;

import app.model.FieldType;

import java.util.List;

public interface FieldTypeService extends BaseService<Integer, FieldType> {
    boolean deleteFieldType(Integer id);

    FieldType findByName(String name);

    List<FieldType> loadFieldTypes();
}
