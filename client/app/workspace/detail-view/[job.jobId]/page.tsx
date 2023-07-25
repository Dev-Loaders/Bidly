"use client";
import React, { useEffect, useState } from "react";
import axios from "axios";
import BidForm from "./BidForm";
import Card from "react-bootstrap/Card";
import { useCookies } from "react-cookie";

declare var window: any;

type Job = {
  title: string;
  location: string;
  imageUrl: string;
  materials: boolean;
  description: string;
  jobId: string;
  created: string;
  bids: Bid[];
};

type Bid = {
  bidId: string;
  amount: string;
};

export default function DetailView() {
  const [cookies] = useCookies();
  const jobId = window.location.pathname.split("/")[3];

  const [jobDetails, setJobDetails] = useState<Job | null>(null);
  const [newBid, setNewBid] = useState(0);

  useEffect(() => {
    axios
      .get(`https://bidly.azurewebsites.net/api/jobs/${jobId}`, {
        headers: {
          Authorization: "Bearer " + cookies.token,
        },
      })
      .then((response) => {
        setJobDetails(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [jobId, newBid]);

  return (
    <>
      <Card className="mt-5">
        <Card.Body>
          <Card.Title>{jobDetails?.title}</Card.Title>
          <Card.Img
            variant="top"
            src={`https://bidly.azurewebsites.net/${jobDetails?.imageUrl}`}
          />
          <Card.Text>
            <strong>Location:</strong> {jobDetails?.location}
          </Card.Text>
          <Card.Text>
            <strong>Materials:</strong>{" "}
            {jobDetails?.materials ? "Provided" : "Not Provided"}
          </Card.Text>
          <Card.Text>
            <strong>Description:</strong> {jobDetails?.description}
          </Card.Text>
          <Card.Text>
            <strong>Created:</strong> {jobDetails?.created}
          </Card.Text>
          <Card.Text>
            {jobDetails?.bids?.length &&
              jobDetails.bids.map((bid) => (
                <div key={bid.bidId}>
                  <strong>Bids:</strong> {bid.amount} kr
                </div>
              ))}
          </Card.Text>
        </Card.Body>
      </Card>

      {jobDetails && <BidForm jobId={jobDetails.jobId} setNewBid={setNewBid} />}
    </>
  );
}
