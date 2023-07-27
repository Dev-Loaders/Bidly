import { Container, Typography, Box } from "@mui/material";
import HomeNav from "../HomeNav";
import Footer from "../Footer";

export default function About() {
  return (
    <>
      <HomeNav></HomeNav>
      <Container>
        <Box p={3}>
          <Typography
            variant="h1"
            style={{
              fontFamily: "Vollkorn",
              marginBottom: "20px",
              fontSize: "60px",
              color: "#242424",
            }}
          >
            About Bidly
          </Typography>
          <Typography variant="body1" style={{ marginBottom: "20px" }}>
            Bidly is an innovative platform developed by Vanessa Wingårdh and
            Ibrahim Iqbal. Bidly was brought to life during an intensive
            two-week project at SALT. For this project, we unanimously decided
            to create a solution-oriented project. We wondered, &quot;If you
            needed to install recessed lights, also known as infällda lampor in
            Swedish, who would you call?&quot; What if there was a way to
            connect with a local electrician for a quote, instead of engaging
            with a commercial company?
          </Typography>

          <Typography variant="body1" style={{ marginBottom: "20px" }}>
            With Bidly, we&apos;ve created a unique solution that connects
            homeowners directly with local trade persons, allowing them to bid
            on posted projects. This approach not only ensures homeowners get
            the most competitive price, but it also provides an opportunity for
            trade persons to secure additional income through side jobs.
          </Typography>

          <Typography variant="body1" style={{ marginBottom: "20px" }}>
            Despite the prevalence of platforms like Fiverr, which enable users
            to advertise their skills for hire, we noticed a void in the market.
            There wasn&apos;t an application that catered specifically to those
            needing tasks done, rather than those offering to complete them. To
            fill this gap, we developed Bidly, a platform designed to serve as a
            conduit between homeowners seeking trade persons and professionals
            looking for additional work.
          </Typography>

          <Typography variant="body1" style={{ marginBottom: "20px" }}>
            Now, let&apos;s explore the core features of Bidly.
          </Typography>

          <Typography variant="body1" style={{ marginBottom: "20px" }}>
            Firstly, users can create an account that undergoes an
            authentication and validation process. Once logged in, they are
            redirected to their workspace, where they can explore the array of
            projects posted on the app. If a particular project catches their
            interest, they can delve deeper into the details and place a bid.
          </Typography>

          <Typography variant="body1" style={{ marginBottom: "20px" }}>
            Users can also utilize the &apos;Publish Project&apos; feature,
            where they have the opportunity to post their own projects on Bidly.
            This involves providing a title, an image, a description, the
            location, and specifying if they will provide any necessary
            materials for the job. For convenience, users can visit the
            &apos;Your Projects&apos; tab to oversee all their posted projects.
            The &apos;Your Bids&apos; tab, on the other hand, allows them to
            keep track of all the bids they have made.
          </Typography>

          <Typography variant="body1" style={{ marginBottom: "20px" }}>
            In conclusion, Bidly strives to revolutionize the way homeowners
            connect with trade persons, providing an easy-to-use, efficient, and
            user-centric solution. Whether you&apos;re looking to get a job done
            or you&apos;re a trades person looking for additional work, Bidly is
            your go-to platform.
          </Typography>
        </Box>
      </Container>

      <Footer></Footer>
    </>
  );
}
