"use client"
import { getUserSubjectFromCookie } from "@/app/TokenGetter";
import axios from "axios";
import { useEffect, useState } from "react";
import { Card, Col, Container, Row } from "react-bootstrap";
import { useCookies } from "react-cookie";

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
    const [cookies] = useCookies();

    const userSub = getUserSubjectFromCookie(cookies);
    // const [jobDetails, setJobDetails] = useState<Job | null>(null);

//   const token = sessionStorage.getItem("token");

  const getUserBids = () => {
    axios
      .get("http://localhost:8080/api/users/" + userSub + "/bids", {
        headers: {
          Authorization: "Bearer ",
        },
      })
      .then((response) => {
        setUserBids(response.data);
        console.log(response.data)
      })
      .catch((error) => {
        console.error(error);
      });
  };

  useEffect(() => {
    getUserBids();
    // getJobDxetails();
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
              </Card>
            </Col>
          ))}
      </Row>
    </Container>
    </main>
  );
}
