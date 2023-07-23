"use client"
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";
import { Card, Col, Container, ListGroup, Row } from "react-bootstrap";

type Job = {
    jobId: string;
    title: string;
    location: string;
    imageUrl: string;
    materials: boolean;
    description: string;
};

export default function UserJobs() {
  const [userJobs, setUserJobs] = useState<Job[]>([]);

//   const token = sessionStorage.getItem("token");

  const getUserJobs = () => {
    axios
      .get("http://localhost:8080/api/users/" + "109019647377227797987" + "/jobs", {
        headers: {
          Authorization: "Bearer ",
        },
      })
      .then((response) => {
        setUserJobs(response.data);
        console.log(response.data)
      })
      .catch((error) => {
        console.error(error);
      });
  };

  useEffect(() => {
    getUserJobs();
  }, []);

  const handleClick = (job: Job) => {
    const jobId = job.jobId;
    window.location.href = `/workspace/detail-view/${jobId}`;
  };

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <Container style={{ minHeight: "100vh" }}>
      <Row>
        {userJobs &&
          userJobs.map((job) => (
            <Col sm={12} md={6} lg={4} xl={3} className="mb-4" key={job.jobId}>
              <Card style={{ width: "100%" }}>
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
                    <ListGroup.Item>
                      Materials: {job.materials ? "Provided" : "Not provided"}
                    </ListGroup.Item>
                  </ListGroup>
              </Card>
            </Col>
          ))}
      </Row>
    </Container>
    </main>
  );
}
