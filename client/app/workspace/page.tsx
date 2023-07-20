"use client"
import React, { useEffect } from 'react';
import axios from 'axios';

export default function Workspace() {

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
            })
            .catch(error => {
                console.error(error);
            });
    };

    useEffect(() => {
        getJobs();
    }, []);

    return (
        <main className="flex min-h-screen flex-col items-center justify-center p-24">
            <div>GET /api/jobs</div>
        </main>
    )
}
