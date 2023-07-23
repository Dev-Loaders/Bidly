package com.bidly.bidly.bid;

import com.bidly.bidly.job.Job;
import com.bidly.bidly.user.BidlyUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id", nullable = false)
    private Long bidId;

    @Column(name = "bid_amount", nullable = false)
    private int amount;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    private String userId;

    private String jobTitle;

    @PrePersist
    private void onCreate() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        this.updated = LocalDateTime.now();
    }

    public Bid() {

    }

    public Bid(String userId, int amount, String jobTitle) {
        this.amount = amount;
        this.userId = userId;
        this.jobTitle = jobTitle;
    }

    public Long getBidId() {
        return bidId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
