import {
  Container,
  Typography,
  Box,
  Grid,
  Link,
  CardContent,
  Card,
  CardMedia,
} from "@mui/material";
import { FaGithub, FaLinkedin, FaGlobe } from "react-icons/fa";
import "../globals.css";
import HomeNav from "../HomeNav";
import Footer from "../Footer";

export default function Team() {
  const profiles = [
    {
      name: "Vanessa Wingårdh",
      image:
        "https://media.licdn.com/dms/image/D4D03AQHiBUfufucCmA/profile-displayphoto-shrink_400_400/0/1688910558090?e=1695859200&v=beta&t=t4w4ZZ8qTIiH8wGg__AYthP8i3CpVe0faugHogaVp7A",
      socials: {
        GitHub: "https://github.com/vwingardh",
        LinkedIn: "https://www.linkedin.com/in/vwingardh/",
        Portfolio: "https://www.vanessawingardh.com",
      },
    },
    {
      name: "Ibrahim Iqbal",
      image:
        "https://media.licdn.com/dms/image/D4D03AQGLAkaNDEHyWQ/profile-displayphoto-shrink_400_400/0/1673879320037?e=1695859200&v=beta&t=jVM0NwzeKXi7xYubXh-1jkkRdcM2SQUNRSY_Gs44bQo",
      socials: {
        GitHub: "https://github.com/dIB59",
        LinkedIn: "https://www.linkedin.com/in/ibrahim-iqbal-34a5b617a/",
      },
    },
  ];

  return (
    <>
      <HomeNav />

      <Container>
        <Box p={3} style={{ marginBottom: "100px" }}>
          <Typography
            variant="h1"
            style={{
              fontFamily: "Vollkorn",
              marginBottom: "20px",
              fontSize: "60px",
              color: "#242424",
            }}
          >
            The Team
          </Typography>
          <Typography style={{ marginBottom: "40px" }}>
            Vanessa Wingårdh and Ibrahim Iqbal, co-developed the platform,
            Bidly, as part of their collaborative project during the SALT 2023
            Summer Program at SALT.
          </Typography>

          <Grid container spacing={4}>
            {profiles.map((profile, index) => (
              <Grid
                item
                xs={12}
                sm={6}
                key={index}
                style={{ textAlign: "center" }}
              >
                <Card
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    border: "none",
                    boxShadow: "none",
                  }}
                >
                  <CardMedia
                    component="img"
                    image={profile.image}
                    title={profile.name}
                    style={{
                      height: "300px",
                      width: "300px",
                      borderRadius: "50%",
                      objectFit: "cover",
                    }}
                  />
                  <CardContent>
                    <Typography variant="h5">{profile.name}</Typography>
                    <Box display="flex" justifyContent="center" gap="10px">
                      {Object.entries(profile.socials).map(([key, value]) => (
                        <Link
                          key={key}
                          href={value}
                          target="_blank"
                          rel="noopener"
                          style={{ marginTop: "20px" }}
                        >
                          {key === "GitHub" && (
                            <FaGithub
                              className="social-links"
                              style={{ fontSize: "30px" }}
                            />
                          )}
                          {key === "LinkedIn" && (
                            <FaLinkedin
                              className="social-links"
                              style={{ fontSize: "30px" }}
                            />
                          )}
                          {key === "Portfolio" && (
                            <FaGlobe
                              className="social-links"
                              style={{ fontSize: "30px" }}
                            />
                          )}
                        </Link>
                      ))}
                    </Box>
                  </CardContent>
                </Card>
              </Grid>
            ))}
          </Grid>
        </Box>
      </Container>

      <Footer />
    </>
  );
}
