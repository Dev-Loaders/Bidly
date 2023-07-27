import { Typography, Container, Link } from "@mui/material";
import "./globals.css";

export default function Footer() {
  return (
    <footer>
      <Container maxWidth="lg">
        <Typography
          variant="h6"
          align="center"
          gutterBottom
          style={{ fontWeight: "600", fontFamily: "Vollkorn" }}
        >
          <Link href={`/`} style={{ textDecoration: "none", color: "#242424" }}>
            Bidly
          </Link>
        </Typography>
        <Typography
          variant="body2"
          align="center"
          style={{ marginBottom: "2%" }}
        >
          Created with &#128420; by{" "}
          <Link
            href={`https://www.linkedin.com/in/vwingardh/`}
            style={{ fontWeight: "600", color: "#242424" }}
          >
            Vanessa
          </Link>{" "}
          &{" "}
          <Link
            href={`https://www.linkedin.com/in/ibrahim-iqbal-34a5b617a/`}
            style={{ fontWeight: "600", color: "#242424" }}
          >
            Ibrahim
          </Link>
        </Typography>
      </Container>
    </footer>
  );
}
