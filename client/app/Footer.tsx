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
          Bidly
        </Typography>
        <Typography
          variant="body2"
          color="textSecondary"
          align="center"
          style={{ marginBottom: "2%" }}
        >
          Created by{" "}
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
            Ibrahim{" "}
          </Link>
          {new Date().getFullYear()}
          {"."}
        </Typography>
      </Container>
    </footer>
  );
}
