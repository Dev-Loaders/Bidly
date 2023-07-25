"use client"
import axios from "axios";
import { useEffect, useState } from "react";
import { Button, Card, Col, Container, Row } from "react-bootstrap";

type Bid = {
    bidId: string;
    amount: string;
    jobTitle: string;
}

type Job = {
  title: string;
  location: string;
  imageUrl: string;
  materials: boolean;
  description: string;
  jobId: string;
  created: string;
};

export default function UserBids() {
    const [userBids, setUserBids] = useState<Bid[]>([]);

//   const token = sessionStorage.getItem("token");

  const getUserBids = () => {
    axios
      .get("http://localhost:8080/api/users/" + "109019647377227797987" + "/bids", {
        headers: {
          Authorization: "Bearer ",
        },
      })
      .then((response) => {
        setUserBids(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  };

  useEffect(() => {
    getUserBids();
  }, []);

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24">
      <Container style={{ minHeight: "100vh" }}>
      <Row>
        {userBids &&
          userBids.map((bid) => (
            <Col sm={12} md={6} lg={4} xl={3} className="mb-4" key={bid.bidId}>
              <Card style={{ width: "100%" }}>
                  <Card.Body>
                    <Card.Title>Job: {bid.jobTitle}</Card.Title>
                    <Card.Title>Bid: {bid.bidId}</Card.Title>
                    <Card.Text>Bid amount: {bid.amount}</Card.Text>
                  </Card.Body>
                  <Button>Job is Completed</Button>
              </Card>
            </Col>
          ))}
      </Row>
    </Container>
    </main>
  );
}
