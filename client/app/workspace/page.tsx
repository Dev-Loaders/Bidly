"use client";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { Container, Card, ListGroup, Button, Col, Row } from "react-bootstrap";
import Link from "next/link";

type Job = {
  jobId: string;
  title: string;
  location: string;
  imageUrl: string;
  materials: boolean;
  description: string;
};

export default function Workspace() {
  const [allJobs, setAllJobs] = useState<Job[]>([]);

  const getJobs = () => {
    axios
      .get("http://localhost:8080/api/jobs", {
        headers: {
          Authorization: "Bearer ",
        },
      })
      .then((response) => {
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
    const jobId = job.jobId;
    window.location.href = `/workspace/detail-view/${jobId}`;
  };

  return (
    <Container style={{ minHeight: "100vh" }}>
      <Row>
        {allJobs &&
          allJobs.map((job) => (
            <Col sm={12} md={6} lg={4} xl={3} className="mb-4" key={job.jobId}>
              <Card style={{ width: "100%" }} onClick={() => handleClick(job)}>
                <Link
                  href={`/workspace/detail-view/${job.jobId}`}
                  target="_blank"
                >
                  <Card.Img
                    variant="top"
                    src={`http://localhost:8080/${job.imageUrl}`}
                    alt="Job"
                  />
                  <Card.Body>
                    <Card.Title>{job.title}</Card.Title>
                    <Card.Text>{job.description}</Card.Text>
                  </Card.Body>
                  <ListGroup variant="flush">
                    <ListGroup.Item>Location: {job.location}</ListGroup.Item>
                  </ListGroup>
                </Link>
                <Button>Bid</Button>
              </Card>
            </Col>
          ))}
      </Row>
    </Container>
  );
}
