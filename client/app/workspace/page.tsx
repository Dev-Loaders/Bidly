"use client";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { Container, Card, ListGroup, Button } from "react-bootstrap";
import Link from "next/link";

type Job = {
  id: string;
  title: string;
  location: string;
  image: string;
  materials: boolean;
  description: string;
};

export default function Workspace() {
  const [allJobs, setAllJobs] = useState<Job[]>([]);

  const token = sessionStorage.getItem("token");
  console.log(token)

  const getJobs = () => {
    axios
      .get("http://localhost:8080/api/jobs", {
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer ",
        },
      })
      .then((response) => {
        console.log(response.data);
        setAllJobs(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  useEffect(() => {
    getJobs();
  }, []);

  const handleClick = (job: Job) => {
    const jobId = job.id;
    window.location.href = `/workspace/detail-view/${jobId}`;
  };

  return (
    <Container
      className="d-flex justify-content-center align-items-center"
      style={{ minHeight: "100vh" }}
    >
      {allJobs &&
        allJobs.map((job) => (
          <Card className="mb-4" key={job.id} style={{ width: "18rem" }}>
            <Link
              href={`/workspace/detail-view/${job.id}`}
              onClick={() => handleClick(job)}
            >
              <Card.Img
                variant="top"
                src={`http://localhost:8080/${job.image}`}
                alt="Job"
              />
              <Card.Body>
                <Card.Title>{job.title}</Card.Title>
                <Card.Text>{job.description}</Card.Text>
              </Card.Body>
              <ListGroup variant="flush">
                <ListGroup.Item>Location: {job.location}</ListGroup.Item>
                <ListGroup.Item>
                  Materials: {job.materials ? "Provided" : "Not provided"}
                </ListGroup.Item>
              </ListGroup>
            </Link>
          </Card>
        ))}
    </Container>
  );
}
