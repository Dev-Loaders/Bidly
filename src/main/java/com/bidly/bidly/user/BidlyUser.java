package com.bidly.bidly.user;

import com.bidly.bidly.job.Job;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bidly_user")
public class BidlyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidly_id")
    private Long id;
    @Column(name = "jwt_id")
    private String jwtId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;

    public BidlyUser() {

    }

    public BidlyUser(Long id, String jwtId, String fullName, String email) {
        this.id = id;
        this.jwtId = jwtId;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
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
}
