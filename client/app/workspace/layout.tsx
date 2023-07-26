"use client";
import Link from "next/link";
import { Nav, Navbar } from "react-bootstrap";
import "../globals.css";

export default function WorkspaceLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <>
      <Navbar expand="lg" style={{ backgroundColor: "#242424" }}>
        <Navbar.Brand>
          <Link
            href={`/workspace`}
            style={{
              color: "#fff",
              textDecoration: "none",
              fontFamily: "Vollkorn",
              fontWeight: "600",
              marginLeft: "30px",
            }}
          >
            Bidly
          </Link>
        </Navbar.Brand>
        <Navbar.Toggle
          className="custom-toggler"
          aria-controls="basic-navbar-nav"
          style={{ marginRight: "30px", color: "#fff" }}
        />
        <Navbar.Collapse id="basic-navbar-nav" style={{ color: "#fff" }}>
          <Nav className="mr-auto" style={{ marginLeft: "30px" }}>
            <Nav.Link style={{ color: "#fff" }} href={`/workspace`}>
              All Projects
            </Nav.Link>
            <Nav.Link style={{ color: "#fff" }} href={`/workspace/publish-project`}>
              Publish Project
            </Nav.Link>
            <Nav.Link style={{ color: "#fff" }} href={`/workspace/user-projects`}>
              Your Projects
            </Nav.Link>
            <Nav.Link style={{ color: "#fff" }} href={`/workspace/bids`}>
              Your Bids
            </Nav.Link>
          </Nav>
          <Nav className="ml-auto">
            <Nav.Link
              className="me-4"
              style={{ color: "#fff" }}
              href={`http://localhost:8080/login?logout`}
            >
              Logout
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <main>{children}</main>
    </>
  );
}
