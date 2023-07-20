"use client"
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Container, Card, ListGroup } from 'react-bootstrap';


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

    const getJobs = () => {
        axios.get('http://localhost:8080/api/jobs', {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        }) 
            .then(response => {
                console.log(response.data);
                setAllJobs(response.data);
            })
            .catch(error => {
                console.error(error);
            });
    };

    useEffect(() => {
        getJobs();
    }, []);

    return (
        <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: "100vh" }}>
          {allJobs && allJobs.map((job) => (
            <Card className="mb-4" key={job.id} style={{ width: '18rem' }}>
              <Card.Img variant="top" src={`http://localhost:8080/${job.image}`} alt="Job" />
              <Card.Body>
                <Card.Title>{job.title}</Card.Title>
                <Card.Text>
                  {job.description}
                </Card.Text>
              </Card.Body>
              <ListGroup variant="flush">
                <ListGroup.Item>Location: {job.location}</ListGroup.Item>
                <ListGroup.Item>Materials: {job.materials ? 'Provided' : 'Not provided'}</ListGroup.Item>
              </ListGroup>
            </Card>
          ))}
        </Container>
      );
}
