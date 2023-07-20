"use client"
import React from 'react';
import { Navbar, Nav, Button, Container, Row, Col, Image, Card } from 'react-bootstrap';
import Link from 'next/link';
import 'bootstrap/dist/css/bootstrap.min.css';
import './globals.css';


export default function Home() {

  return (
    <main>
      <Navbar bg="light" expand="lg">
        <Navbar.Brand href="/">Bidly</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link href="/about">About</Nav.Link>
            <Nav.Link href="/contact">Contact</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link href="https://bidly-app.azurewebsites.net/login">
              <Button variant="outline-primary">Login</Button>
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>

      <header className="position-relative">
        <Image src="/bidly-images/kitchen.jpg" alt="Bright kitchen with modern design" fluid />
        <div className="centered">
          <h1>Bidly</h1>
          <h2>Compare prices and select the best offer for your projects</h2>
          <Button variant="outline-primary">Sign Up</Button>
        </div>
      </header>

      <Container>
        <Row>
          <Col xs={6} md={3} className="d-flex align-items-center mb-4 mt-4">
            <Card>
              <Image className="icon-img" src="/bidly-images/hammer.svg" alt="Hammer icon" />
              <Card.Body>
                <h5>Qualified trade workers</h5>
                <p>Get quotes from qualified trade workers in your area.</p>
              </Card.Body>
            </Card>
          </Col>

          <Col xs={6} md={3} className="d-flex align-items-center">
            <Card>
              <Image className="icon-img" src="/bidly-images/calendar-day.svg" alt="Calendar icon" />
              <Card.Body>
                <h5>Simple and convenient</h5>
                <p>Get quotes and compare prices in just a few clicks.</p>
              </Card.Body>
            </Card>
          </Col>

          <Col xs={6} md={3} className="d-flex align-items-center mb-4">
            <Card>
              <Image className="icon-img" src="/bidly-images/hand-thumbs-up.svg" alt="Thumbs up icon" />
              <Card.Body>
                <h5>Protected projects</h5>
                <p>Your projects are protected by our secure bidding platform.</p>
              </Card.Body>
            </Card>
          </Col>

          <Col xs={6} md={3} className="d-flex align-items-center mb-4">
            <Card>
              <Image className="icon-img" src="/bidly-images/tag.svg" alt="Price icon" />
              <Card.Body>
                <h5>Compare prices</h5>
                <p>Get quotes from multiple trade workers and compare prices.</p>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>

      {/* <Image className="img--bottom" src="/bidly-images/bathroom.jpg" alt="Bright bathroom with modern design" fluid /> */}

      <footer className="footer mt-auto py-3 bg-light">
        <Container>
          <span className="text-muted">© 2023 Bidly</span>
        </Container>
      </footer>
    </main>
  );
}
