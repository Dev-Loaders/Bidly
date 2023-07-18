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
    private Long id;

    @Column(name = "bid_amount", nullable = false)
    private int amount;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "updated")
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "bidly_id")
    private BidlyUser user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @PrePersist
    private void created() {
        this.created = LocalDateTime.now();
    }

    public Bid() {

    }

    public Bid(Long id, int amount, LocalDateTime created, LocalDateTime updated, BidlyUser user, Job job) {
        this.id = id;
        this.amount = amount;
        this.created = created;
        this.updated = updated;
        this.user = user;
        this.job = job;
    }

    public Long getId() {
        return id;
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

    public BidlyUser getUser() {
        return user;
    }

    public void setUser(BidlyUser user) {
        this.user = user;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
