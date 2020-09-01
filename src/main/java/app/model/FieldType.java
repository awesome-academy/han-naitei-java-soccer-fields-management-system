package app.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "field_types")
public class FieldType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_url")
    private String photoURL;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fieldType")
    private List<Field> fields;

    public FieldType() {
    }

    public FieldType(int id, String name, String description, String photoURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photoURL = photoURL;
    }

    public FieldType(String name, String description, String photoURL) {
        this.name = name;
        this.description = description;
        this.photoURL = photoURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public Date getUpdateAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "FieldType{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoURL='" + photoURL + '\'' +
                '}';
    }
}
