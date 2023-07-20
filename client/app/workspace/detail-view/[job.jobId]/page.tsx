"use client";
import React, { useEffect, useState } from "react";
import axios from "axios";
import BidForm from "./BidForm";
import Card from 'react-bootstrap/Card';

type Job = {
  title: string;
  location: string;
  image: string;
  materials: boolean;
  description: string;
  jobId: string;
};

export default function DetailView() {
  const jobId = window.location.href.split("/")[5];
  const token = sessionStorage.getItem("token");

  const [jobDetails, setJobDetails] = useState<Job | null>(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/jobs/${jobId}`, {
        headers: {
          Authorization: "Bearer ",
        },
      })
      .then((response) => {
        setJobDetails(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [jobId]);

  return (
    <>
      <Card className="mt-5">
        <Card.Body>
          <Card.Title>{jobDetails?.title}</Card.Title>
          <Card.Text>
            <strong>Location:</strong> {jobDetails?.location}
          </Card.Text>
          <Card.Img variant="top" src={jobDetails?.image} />
          <Card.Text>
            <strong>Materials:</strong>{" "}
            {jobDetails?.materials ? "Provided" : "Not Provided"}
          </Card.Text>
          <Card.Text>
            <strong>Description:</strong> {jobDetails?.description}
          </Card.Text>
        </Card.Body>
      </Card>

      {jobDetails && <BidForm jobId={jobDetails.jobId} />}
    </>
  );
}
