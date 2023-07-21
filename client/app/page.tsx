"use client"
import React from 'react';
import { Navbar, Nav, Button, Container, Row, Col, Image, Card } from 'react-bootstrap';
import Link from 'next/link';
import 'bootstrap/dist/css/bootstrap.min.css';
import './globals.css';
import { TokenCookie } from './TestCookie';

export default function Home() {

  return (
    <main>
      <TokenCookie />
    </main>
  );
}
