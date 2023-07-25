package com.bidly.bidly.user;

//import com.bidly.bidly.bid.Bid;
import com.bidly.bidly.bid.Bid;
import com.bidly.bidly.job.Job;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bidly_user")
public class BidlyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "jwt_id")
    private String jwtId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids;

    public BidlyUser() {
    }

    public BidlyUser(String jwtId, String fullName, String email) {
        this.jwtId = jwtId;
        this.fullName = fullName;
        this.email = email;
//        this.jobs = jobs;
//        this.bids = bids;
    }
  
    public Long getUserId() {
        return userId;
    }

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void addJobs(Job job) {
        jobs.add(job);
    }
}
