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
      <Navbar expand="lg" style={{ backgroundColor: "#fff" }}>
        <Navbar.Brand>
          <Link
            href={`/workspace`}
            style={{
              color: '#242424',
              textDecoration: "none",
              fontFamily: "Montserrat",
              marginLeft: "30px",
            }}
          >
            Bidly
          </Link>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" style={{ marginRight: '30px' }} />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="mr-auto" style={{ color: 'red', marginLeft: '30px' }}>
            <Nav.Link href={`/workspace`}>All Projects</Nav.Link>
            <Nav.Link href={`/workspace/post-job`}>Publish Project</Nav.Link>
            <Nav.Link href={`/workspace/user-jobs`}>
              Your Projects
            </Nav.Link>
            <Nav.Link href={`/workspace/user-bids`}>
              Your Bids
            </Nav.Link>
            <Nav.Link href={`https://bidly.azurewebsites.net/login?logout`}>
              Logout
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Navbar>
      <main>{children}</main>
    </>
  );
}
