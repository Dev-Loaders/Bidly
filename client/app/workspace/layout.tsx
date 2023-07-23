"use client"
import Link from "next/link"
import { Nav, Navbar } from "react-bootstrap"

export default function WorkspaceLayout({
    children,
}: {
    children: React.ReactNode
}) {
    return (
        <>
            <Navbar bg="light" expand="lg">
                <Navbar.Brand><Link href={`/workspace`}>Bidly</Link></Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="mr-auto">
                        <Nav.Link href={`/workspace`}>Jobs</Nav.Link>
                        <Nav.Link href={`/workspace/post-job`}>Create Job</Nav.Link>
                        <Nav.Link href={`/workspace/user-jobs`}>Your Jobs</Nav.Link>
                        <Nav.Link href={`/workspace/user-bids`}>Your Bids</Nav.Link>
                        <Nav.Link href={`http://localhost:8080/login?logout`}>Logout</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Navbar>
            <main>
                {children}
            </main>
            </>
    )
}
