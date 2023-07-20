"use client";
import React, { useEffect, useState } from "react";
import axios from "axios";

export default function DetailView() {
    const jobId = window.location.href.split("/")[4];
    const token = sessionStorage.getItem("token");

    const [jobDetails, setJobDetails] = useState<Job | null>(null);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/jobs/${jobId}`, {
        headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer ",
        },
        })
        .then((response) => {
        setJobDetails(response.data)
        })
        .catch((error) => {
        console.error(error);
        });
    }, [jobId]);

  return (
    <div>
      <h1>{jobDetails?.title}</h1>
    </div>
  );
}
