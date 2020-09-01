package app.info;

import app.model.FieldType;

public class FieldTypeInfo {

    private Integer id;

    private String name;

    private String description;

    private String photoURL;

    public FieldTypeInfo(Integer id, String name, String description, String photoURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoURL = photoURL;
    }

    public FieldTypeInfo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public FieldType toFieldType() {
        if (this.id != null)
            return new FieldType(this.id, this.name, this.description, this.photoURL);
        return new FieldType(this.name, this.description, this.photoURL);
    }
}
