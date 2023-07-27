"use client";
import React, { useEffect, useState } from "react";
import axios from "axios";
import BidForm from "./BidForm";

import {
  Box,
  Card,
  CardContent,
  CardMedia,
  Container,
  Grid,
  Typography,
} from "@mui/material";

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
  const [newBid, setNewBid] = useState<number>(0);

  useEffect(() => {
    axios
      .get(`https:bidly-app.azurewebsites.net/api/jobs/${jobId}`, {
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
  }, [cookies.token, jobId, newBid]);

  return (
    <>
      <Container maxWidth="lg">
        <Grid container spacing={3}>
          <Grid item xs={12} md={6}>
            <Card className="mt-5">
              <CardContent>
                <Typography variant="h5" gutterBottom>
                  {jobDetails?.title}
                </Typography>
                <CardMedia
                  component="img"
                  image={`https:bidly-app.azurewebsites.net/${jobDetails?.imageUrl}`}
                />
                <Box mt={2}>
                  <Typography gutterBottom>
                    <strong>Location:</strong> {jobDetails?.location}
                  </Typography>

                  <Typography gutterBottom>
                    <strong>Materials:</strong>{" "}
                    {jobDetails?.materials ? "Provided" : "Not Provided"}
                  </Typography>

                  <Typography gutterBottom>
                    <strong>Description:</strong> {jobDetails?.description}
                  </Typography>

                  <Typography gutterBottom>
                    <strong>Created:</strong>{" "}
                    {jobDetails?.created?.substring(0, 10)}
                  </Typography>

                  <Typography gutterBottom>
                    <strong>Bids: </strong>
                    {jobDetails?.bids?.length &&
                      jobDetails.bids.map((bid) => (
                        <div key={bid.bidId}>{bid.amount} kr</div>
                      ))}
                  </Typography>
                </Box>
              </CardContent>
            </Card>
          </Grid>
          <Grid item xs={12} md={6}>
            {jobDetails && (
              <BidForm jobId={jobDetails.jobId} setNewBid={setNewBid} />
            )}
          </Grid>
        </Grid>
      </Container>
    </>
  );
}
