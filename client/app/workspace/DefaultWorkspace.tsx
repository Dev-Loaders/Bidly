// "use client";
// import React, { useEffect, useState } from "react";
// import axios from "axios";
// import Link from "next/link";
// import {
//   Box,
//   Button,
//   Card,
//   CardContent,
//   CardMedia,
//   Grid,
//   Typography,
// } from "@mui/material";
// import { TokenSyntaxKind } from "typescript";
// import jwtDecode from "jwt-decode";

// type Job = {
//   jobId: string;
//   title: string;
//   location: string;
//   imageUrl: string;
//   materials: boolean;
//   description: string;
// };

// type Token = {
//     token: string | null;
// }

// export default function DefaultWorkspace({ token }: Token) {
//   const [allJobs, setAllJobs] = useState<Job[]>([]);

//   const getJobs = () => {
//     axios
//       .get("https://bidly.azurewebsites.net/api/jobs", {
//         headers: {
//           Authorization: "Bearer " + token,
//         },
//       })
//       .then((response) => {
//         setAllJobs(response.data);
//       })
//       .catch((error) => {
//         console.error(error);
//       });
//   };

//   useEffect(() => {
//     getJobs();
//   }, []);

//   const handleClick = (job: Job) => {
//     const jobId = job.jobId;
//     window.location.href = `/workspace/detail-view/${jobId}`;
//   };

//   return (
//     <>
//       <Box
//         style={{
//           padding: "6%",
//           paddingTop: "8%",
//           paddingBottom: "8%",
//           backgroundColor: "#f0f0f0",
//         }}
//       >
//         <Typography
//           variant="h3"
//           style={{
//             fontSize: "32px",
//             fontWeight: "400",
//             marginBlockEnd: "4%",
//             color: "#555",
//           }}
//         >
//           Projects on Bidly
//         </Typography>
//         <Typography variant="h5" style={{ fontSize: "16px", color: "#242424" }}>
//           Each project listed here is a chance to showcase your expertise, help
//           your neighbors, and grow your business all at once. Explore the posts
//           below, find the ones that align with your skills and interests, and
//           start bidding!
//         </Typography>
//       </Box>

//       <Box
//         minHeight="100vh"
//         style={{ marginLeft: "4%", marginRight: "4%", marginBlockStart: "4%", marginBlockEnd: "4%" }}
//       >
//         <Grid container spacing={2}>
//           {allJobs &&
//             allJobs.map((job) => (
//               <Grid item xs={12} sm={6} md={4} lg={3} key={job.jobId}>
//                 <Card
//                   style={{ width: "100%" }}
//                   onClick={() => handleClick(job)}
//                 >
//                   <Link
//                     href={`/workspace/detail-view/${job.jobId}`}
//                     target="_blank"
//                     style={{ color: "#242424", textDecoration: "none" }}
//                   >
//                     {" "}
//                     <CardMedia
//                       component="img"
//                       alt="Job"
//                       height="300"
//                       image={`http://localhost:8080/${job.imageUrl}`}
//                     />
//                     <CardContent>
//                       <Typography variant="h5" component="div">
//                         {job.title.charAt(0).toUpperCase() + job.title.slice(1)}
//                       </Typography>
//                       <Typography variant="body2" color="text.secondary">
//                         {job.description.charAt(0).toUpperCase() +
//                           job.description.slice(1, 50)}
//                         {job.description.length > 100 ? "..." : ""}
//                       </Typography>
//                     </CardContent>
//                   </Link>
//                   <Box
//                     style={{
//                       display: "flex",
//                       justifyContent: "center",
//                       flexGrow: 1,
//                     }}
//                   >
//                     <Button
//                       variant="outlined"
//                       color="inherit"
//                       style={{ marginBottom: "4%", padding: "6px 50px" }}
//                     >
//                       Bid
//                     </Button>
//                   </Box>
//                 </Card>
//               </Grid>
//             ))}
//         </Grid>
//       </Box>
//     </>
//   );
// }
