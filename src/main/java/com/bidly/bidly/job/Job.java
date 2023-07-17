package com.bidly.bidly.job;

import com.bidly.bidly.user.BidlyUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false, length = 150)
    private String title;
    @Column(name = "description", nullable = false, length = 1000)
    private String description;
    @Column(name = "location", nullable = false, length = 50)
    private String location;
    @Column(name = "image_url", nullable = true)
    private String imageUrl;
    @Column(name = "materials")
    private boolean materials;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "updated")
    private LocalDateTime updated;

    @PrePersist
    private void created() {
        this.created = LocalDateTime.now();
    }

    public Job() {

    }

    public Job(Long id, String title, String description, String location, String imageUrl, boolean materials, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
        this.materials = materials;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isMaterials() {
        return materials;
    }

    public void setMaterials(boolean materials) {
        this.materials = materials;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
