"use client";
import Link from "next/link";
import Image from "next/image";
import {
  Typography,
  Button,
  Container,
  Grid,
  Card,
  CardContent,
  Box,
} from "@mui/material";
import "./globals.css";
import HomeNav from "./HomeNav";
import Footer from "./Footer";

export default function Home() {
  return (
    <>
      <main>
        <HomeNav></HomeNav>

        <img
          className="header__img"
          src="/bidly-images/white.jpg"
          alt="Bright kitchen with modern design"
        />

        <Container style={{ marginBlockEnd: "5%" }}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <div
                style={{
                  position: "absolute",
                  top: "50%",
                  left: "50%",
                  transform: "translate(-50%, -50%)",
                  color: "#242424",
                }}
              >
                <Typography
                  variant="h1"
                  style={{
                    fontSize: "45px",
                    fontFamily: "Vollkorn",
                    marginBottom: "4%",
                  }}
                >
                  Bidly
                </Typography>
                <Typography
                  variant="h2"
                  style={{ fontSize: "35px", marginBlockEnd: "10%" }}
                >
                  Compare prices and select the best bid for your projects
                </Typography>
                <Button
                  variant="outlined"
                  color="inherit"
                  style={{ marginBlockEnd: "10%", padding: "6px 50px" }}
                >
                  <Link
                    style={{ textDecoration: "none", color: "#242424" }}
                    href={`https://bidly-app.azurewebsites.net/login`}
                  >
                    Sign Up
                  </Link>
                </Button>
              </div>
            </Grid>
          </Grid>
        </Container>

        <Container maxWidth="sm" style={{ marginBlockEnd: "5%" }}>
          <Grid container spacing={2}>
            <Grid item xs={6}>
              <Card
                className="card"
                style={{ border: "none", boxShadow: "none" }}
              >
                <CardContent>
                  <Box display="flex" alignItems="center">
                    <Image
                      className="icon-img"
                      src="/bidly-images/bids.svg"
                      alt="Bid icon"
                      width="40"
                      height="40"
                    />
                    <Typography
                      variant="h5"
                      style={{ marginLeft: "10px", fontSize: "20px" }}
                    >
                      Multiple Bids
                    </Typography>
                  </Box>
                  <Typography style={{ marginLeft: "50px" }}>
                    Choose the best bid for your project
                  </Typography>
                </CardContent>
              </Card>
            </Grid>

            <Grid item xs={6}>
              <Card
                className="card"
                style={{ border: "none", boxShadow: "none" }}
              >
                <CardContent>
                  <Box display="flex" alignItems="center">
                    <Image
                      className="icon-img"
                      src="/bidly-images/qualified.svg"
                      alt="Qualified icon"
                      width="40"
                      height="40"
                    />
                    <Typography
                      variant="h5"
                      style={{ marginLeft: "10px", fontSize: "20px" }}
                    >
                      Qualified Workers
                    </Typography>
                  </Box>
                  <Typography style={{ marginLeft: "50px" }}>
                    Licensed and qualified trade workers
                  </Typography>
                </CardContent>
              </Card>
            </Grid>

            <Grid item xs={6}>
              <Card
                className="card"
                style={{ border: "none", boxShadow: "none" }}
              >
                <CardContent>
                  <Box display="flex" alignItems="center">
                    <Image
                      className="icon-img"
                      src="/bidly-images/ease.svg"
                      alt="Ease of use icon"
                      width="40"
                      height="40"
                    />
                    <Typography
                      variant="h5"
                      style={{ marginLeft: "10px", fontSize: "20px" }}
                    >
                      Ease of use
                    </Typography>
                  </Box>
                  <Typography style={{ marginLeft: "50px" }}>
                    Post project, review bids, start project
                  </Typography>
                </CardContent>
              </Card>
            </Grid>

            <Grid item xs={6}>
              <Card
                className="card"
                style={{ border: "none", boxShadow: "none" }}
              >
                <CardContent>
                  <Box display="flex" alignItems="center">
                    <Image
                      className="icon-img"
                      src="/bidly-images/reviews.svg"
                      alt="Review icon"
                      width="40"
                      height="40"
                    />
                    <Typography
                      variant="h5"
                      style={{ marginLeft: "10px", fontSize: "20px" }}
                    >
                      Review Ratings
                    </Typography>
                  </Box>
                  <Typography style={{ marginLeft: "50px" }}>
                    Make informed decisions before you hire
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          </Grid>
        </Container>

        <Box className="info-box">
          <Typography
            className="info-box__title"
            variant="h3"
            style={{
              fontSize: "32px",
              fontWeight: "400",
              marginBlockEnd: "4%",
              color: "#242424",
            }}
          >
            Publishing to Bidly
          </Typography>
          <Typography
            className="info-box__content"
            variant="h5"
            style={{ fontSize: "16px", color: "#242424" }}
          >
            What does it cost to publish a project? Publishing to Bidly is free
            of charge. We have a commission fee that is 10% for completed
            projects. For projects with no bids, you will not be charged.
          </Typography>
        </Box>

        <Box style={{ marginBlockEnd: "6%" }}>
          <img
            className="box__img"
            src="/bidly-images/kitchen.jpg"
            alt="Bright kitchen with modern design"
          />
        </Box>

        <Footer></Footer>
      </main>
    </>
  );
}
