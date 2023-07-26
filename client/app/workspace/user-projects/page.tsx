"use client";
import { getUserSubjectFromCookie } from "@/app/TokenGetter";
import {
  Box,
  Card,
  CardContent,
  CardMedia,
  Container,
  Grid,
  Typography,
} from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useCookies } from "react-cookie";

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
  const [cookies] = useCookies();

  const userSubject = getUserSubjectFromCookie(cookies);

  useEffect(() => {
    const getUserJobs = () => {
      axios
        .get("http://localhost:8080/api/users/" + userSubject + "/jobs", {
          headers: {
            Authorization: "Bearer " + cookies.token,
          },
        })
        .then((response) => {
          setUserJobs(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    };

    getUserJobs();
  }, [cookies.token, userSubject]);

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
          Your Projects on Bidly
        </Typography>
        <Typography
          className="info-box__content"
          variant="h5"
          style={{ fontSize: "16px", color: "#242424" }}
        >
          Welcome to your project dashboard on Bidly. This is where you can see
          all the projects you have posted and keep track of their progress.
          Each project listing represents your call to the community, an
          opportunity for skilled workers to bring your vision to life.
        </Typography>
      </Box>

      <main
        style={{
          minHeight: "100vh",
          padding: 24,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <Container style={{ minHeight: "100vh" }}>
          <Box
            sx={{
              ml: { xs: 1, md: 4 },
              mr: { xs: 1, md: 4 },
              mb: { xs: 1, md: 4 },
              mt: { xs: 1, md: 4 },
            }}
          >
            <Grid container spacing={2}>
              {userJobs &&
                userJobs.map((job) => (
                  <Grid item xs={12} sm={6} md={4} lg={3} key={job.jobId}>
                    <Card style={{ width: "100%" }}>
                      <CardMedia
                        component="img"
                        alt="Job"
                        style={{
                          width: "100%",
                          height: "300px",
                          objectFit: "cover",
                          borderTopLeftRadius: "5px",
                          borderTopRightRadius: "5px",
                        }}
                        image={`http://localhost:8080/${job.imageUrl}`}
                      />
                      <CardContent>
                        <Typography variant="h5" component="div">
                          {job.title.charAt(0).toUpperCase() +
                            job.title.slice(1)}
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                          {job.description.charAt(0).toUpperCase() +
                            job.description.slice(1, 50)}
                          {job.description.length > 50 ? "..." : ""}
                        </Typography>
                      </CardContent>
                    </Card>
                  </Grid>
                ))}
            </Grid>
          </Box>
        </Container>
      </main>
    </>
  );
}
