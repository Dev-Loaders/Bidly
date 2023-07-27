"use client";
import axios from "axios";
import { useEffect, useState } from "react";
import { useCookies } from "react-cookie";
import { getUserSubjectFromCookie } from "@/app/TokenGetter";
import {
  Box,
  CardContent,
  Container,
  Grid,
  Paper,
  Typography,
} from "@mui/material";

type Bid = {
  bidId: string;
  amount: string;
  jobTitle: string;
  created: string;
};

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

  const userSubject = getUserSubjectFromCookie(cookies);

  const getUserBids = () => {
    axios
      .get("https://bidly.azurewebsites.net/api/users/" + userSubject + "/bids", {
        headers: {
          Authorization: "Bearer " + cookies.token,
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
    <>
      <Box className="info-box">
        <Typography
          className="info-box__title"
          variant="h3"
          style={{
            fontSize: "32px",
            fontWeight: "400",
            marginBlockEnd: "2%",
            color: "#555",
          }}
        >
          Your Bids on Bidly
        </Typography>
        <Typography
          className="info-box__content"
          variant="h5"
          style={{ fontSize: "16px", color: "#242424", margin: '0 auto' }}
        >
          Welcome to your personal bidding page, where we have gathered all the
          projects you have placed bids on. This is your hub to track the
          progress of your bids, from the moment they are placed to the
          potential acceptance by the project owners.
        </Typography>
      </Box>

      <main
        style={{
          minHeight: "100vh",
          padding: 24,
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <Container style={{ minHeight: "100vh" }}>
          <Grid container spacing={4}>
            {userBids &&
              userBids.map((bid) => (
                <Grid item xs={12} sm={6} md={4} key={bid.bidId}>
                  <Paper
                    style={{
                      width: "100%",
                      boxShadow: "0 4px 8px 0 rgba(0, 0, 0, 0.2)",
                      margin: "0.5rem",
                    }}
                  >
                    <CardContent>
                      <Typography
                        variant="h5"
                        gutterBottom
                      >{`Bid: ${bid.bidId}`}</Typography>
                      <Typography variant="h6" gutterBottom>{`${
                        bid.jobTitle.length > 15
                          ? bid.jobTitle.substring(0, 18) + "..."
                          : bid.jobTitle
                      }`}</Typography>

                      <Typography
                        variant="body1"
                        style={{ marginBottom: "20px", fontWeight: "bold" }}
                      >
                        {`Bid amount: ${bid.amount}`} kr
                      </Typography>

                      <Typography variant="body1">
                        {`Created: ${bid.created.substring(0, 10)}`}
                      </Typography>
                    </CardContent>
                  </Paper>
                </Grid>
              ))}
          </Grid>
        </Container>
      </main>
    </>
  );
}
