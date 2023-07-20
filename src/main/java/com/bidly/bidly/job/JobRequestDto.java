package com.bidly.bidly.job;

//import com.bidly.bidly.bid.Bid;


//@JsonIgnoreProperties(ignoreUnknown = true)
public record JobRequest(String title,
                         String description,
                         String location,
                         String image_url,
                         boolean materials){
}