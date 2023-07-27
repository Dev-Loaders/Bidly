import { getUserSubjectFromCookie } from "@/app/TokenGetter";
import { Box, Button, Container, TextField, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { useCookies } from "react-cookie";
import React from "react";
import Alert from "@mui/material/Alert";

declare var window: any;

type BidFormDataProps = {
  amount: string;
};

interface BidFormProps {
  jobId: string;
}

export default function BidForm({
  jobId,
  setNewBid,
}: BidFormProps & { setNewBid: React.Dispatch<React.SetStateAction<number>> }) {
  const [amount, setAmount] = useState("");
  const [cookies] = useCookies();
  const [showAlert, setShowAlert] = useState(false);

  const handleAmount = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAmount(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    postJob({
      amount: amount,
    });
    setShowAlert(true);
  };

  const userSubject = getUserSubjectFromCookie(cookies);

  const postJob = ({ amount }: BidFormDataProps) => {
    const formData = new FormData();

    formData.append("amount", amount);
    const jobId = window.location.pathname.split("/")[3];

    axios
      .post(
        "https://bidly.azurewebsites.net/api/users/" +
          userSubject +
          "/jobs/" +
          jobId +
          "/bids",
        formData,
        {
          headers: {
            Authorization: "Bearer " + cookies.token,
          },
        }
      )
      .then((response) => {
        setNewBid((prevBid) => prevBid + 1);
      })
      .catch((exception) => console.error(exception));
  };

  return (
    <>
      <Container maxWidth="sm">
        <Box mt={4} mb={2} p={4} boxShadow={3} bgcolor="#fff">
          <Typography variant="h5" gutterBottom align="center">
            Bid on Project
          </Typography>

          <Box
            my={1}
            style={{ width: "100%", borderBottom: "1px solid #ddd" }}
          />
          <form method="post" onSubmit={handleSubmit}>
            {" "}
            <Box my={3}>
              <TextField
                id="amount"
                name="amount"
                label="Bid Amount"
                variant="outlined"
                fullWidth
                required
                onChange={handleAmount}
              />
            </Box>
            {showAlert && (
              <Alert onClose={() => setShowAlert(false)} style={{ marginBottom: '3%' }}>
                Bid posted successfully!
              </Alert>
            )}
            <Button
              variant="outlined"
              color="inherit"
              style={{ marginBottom: "4%", padding: "6px 50px" }}
              type="submit"
              fullWidth
            >
              Submit Bid
            </Button>
          </form>
        </Box>
      </Container>
    </>
  );
}
