"use client"
import { useSearchParams } from 'next/navigation';
import { useCookies } from "react-cookie";
import { Box, Button, Card, CardContent, CardMedia, Grid, Link, Typography } from '@mui/material';
import { useEffect, useState } from 'react';
import axios from 'axios';


type Job = {
  jobId: string;
  title: string;
  location: string;
  imageUrl: string;
  materials: boolean;
  description: string;
};

export default function Workspace() {
  
  const searchParams = useSearchParams();
  const token = searchParams.get('token');
  const [allJobs, setAllJobs] = useState<Job[]>([]);

  const [cookies, setCookie] = useCookies(['token']);
  const setTokenAsCookie = (token: any) => {
    if (token) {
    setCookie('token', token, { path: '/', expires: new Date(Date.now() + 3600000)});
    }
  };
  setTokenAsCookie(token);
  console.log(cookies.token);


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
          Projects on Bidly
        </Typography>
        <Typography
          className="info-box__content"
          variant="h5"
          style={{ fontSize: "16px", color: "#242424" }}
        >
          Each project listed here is a chance to showcase your expertise, help
          your neighbors, and grow your business all at once. Explore the posts
          below, find the ones that align with your skills and interests, and
          start bidding!
        </Typography>
      </Box>

      <Box
        minHeight="100vh"
        style={{
          marginLeft: "4%",
          marginRight: "4%",
          marginBlockStart: "4%",
          marginBlockEnd: "4%",
        }}
      >
        <Grid container spacing={2}>
          {allJobs &&
            allJobs.map((job) => (
              <Grid item xs={12} sm={6} md={4} lg={3} key={job.jobId}>
                <Card
                  style={{ width: "100%" }}
                  onClick={() => handleClick(job)}
                >
                  <Link
                    href={`/workspace/detail-view/${job.jobId}`}
                    target="_blank"
                    style={{ color: "#242424", textDecoration: "none" }}
                  >
                    {" "}
                    <CardMedia
                      component="img"
                      alt="Job"
                      height="300"
                      image={`http://localhost:8080/${job.imageUrl}`}
                    />
                    <CardContent>
                      <Typography variant="h5" component="div">
                        {job.title.charAt(0).toUpperCase() + job.title.slice(1)}
                      </Typography>
                      <Typography variant="body2" color="text.secondary">
                        {job.description.charAt(0).toUpperCase() +
                          job.description.slice(1, 50)}
                        {job.description.length > 100 ? "..." : ""}
                      </Typography>
                    </CardContent>
                  </Link>
                  <Box
                    style={{
                      display: "flex",
                      justifyContent: "center",
                      flexGrow: 1,
                    }}
                  >
                    <Button
                      variant="outlined"
                      color="inherit"
                      style={{ marginBottom: "4%", padding: "6px 50px" }}
                    >
                      Bid
                    </Button>
                  </Box>
                </Card>
              </Grid>
            ))}
        </Grid>
      </Box>
    </>
  );
}
