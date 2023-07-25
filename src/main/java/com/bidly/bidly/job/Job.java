package com.bidly.bidly.job;

import com.bidly.bidly.bid.Bid;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "materials")
    private boolean materials;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Bid> bids;

//    @OneToOne
//    private Bid acceptedBid;

//    private boolean completed;

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @PrePersist
    private void onCreate() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.updated = LocalDateTime.now();
    }

    public Job() {
    }
    public Job(String title, String description, String location, String imageUrl, boolean materials
    ) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
        this.materials = materials;
    }

    public Long getJobId() {
        return jobId;
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

    public List<Bid> getBids() {
        return bids;
    }


//    public Bid getAcceptedBid() {
//        return acceptedBid;
//    }
//
//    public void setAcceptedBid(Bid acceptedBid) {
//        this.acceptedBid = acceptedBid;
//        acceptedBid.setAccepted(true);
//        bids.clear();
//    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

//    public boolean isCompleted() {
//        return completed;
//    }
//
//    public void setCompleted(boolean completed) {
//        this.completed = completed;
//        acceptedBid.setCompleted(true);
//    }

    public void addBids(Bid bid) {
        bids.add(bid);
    }

}
