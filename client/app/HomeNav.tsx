import { AppBar, Toolbar, Typography, Box, Link } from "@mui/material";
import "./globals.css";

export default function HomeNav() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" sx={{ backgroundColor: "#242424" }}>
        <Toolbar>
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, fontWeight: "600", fontFamily: "Vollkorn", fontSize: '23px' }}
          >
            <Link href={`/`} style={{ textDecoration: "none", color: "#fff" }}>
              Bidly
            </Link>
          </Typography>

          <Typography>
            <Link
              href={`/about-bidly`}
              style={{
                textDecoration: "none",
                color: "#fff",
                marginRight: "25px",
              }}
            >
              About Bidly
            </Link>
          </Typography>

          <Typography>
            <Link
              href={`/the-team`}
              style={{
                textDecoration: "none",
                color: "#fff",
                marginRight: "25px",
              }}
            >
              The Team
            </Link>
          </Typography>

          <Link
            style={{ textDecoration: "none", color: "#fff" }}
            href={`https://bidly-app.azurewebsites.net/login`}
          >
            Login
          </Link>
        </Toolbar>
      </AppBar>
    </Box>
  );
}
