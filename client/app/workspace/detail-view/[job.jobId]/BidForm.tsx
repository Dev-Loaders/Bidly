import { getUserSubjectFromCookie } from "@/app/TokenGetter";
import { Box, Button, Container, TextField, Typography } from "@mui/material";
import axios from "axios";
import { useState } from "react";
import { useCookies } from "react-cookie";
import Snackbar from "@material-ui/core/snackbar";
import MuiAlert, { AlertProps } from "@material-ui/lab/alert";
import React from "react";
import { SnackbarCloseReason } from "@material-ui/core";
import { SyntheticEvent } from "react";

declare var window: any;

type BidFormDataProps = {
  amount: string;
};

interface BidFormProps {
  jobId: string;
}

function Alert(props: AlertProps) {
  return <MuiAlert elevation={6} variant="filled" {...props} />;
}

export default function BidForm({
  jobId,
  setNewBid,
}: BidFormProps & { setNewBid: React.Dispatch<React.SetStateAction<number>> }) {
  const [amount, setAmount] = useState("");
  const [cookies] = useCookies();
  const [open, setOpen] = React.useState(false);

  const handleClose = (
    event: SyntheticEvent<Element, Event>,
    reason: SnackbarCloseReason
  ) => {
    if (reason === "clickaway") {
      return;
    }
    setOpen(false);
  };

  const handleAmount = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAmount(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    postJob({
      amount: amount,
    });
    setOpen(true);
  };

  const handleAlertClose = (event: SyntheticEvent<Element, Event>) => {
    setOpen(false);
  };

  const userSubject = getUserSubjectFromCookie(cookies);

  const postJob = ({ amount }: BidFormDataProps) => {
    const formData = new FormData();

    formData.append("amount", amount);
    const jobId = window.location.pathname.split("/")[3];

    axios
      .post(
        "http://localhost:8080/api/users/" +
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

          <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
            <Alert onClose={handleAlertClose} severity="success">
              Your Bid was successfully added!
            </Alert>
          </Snackbar>
        </Box>
      </Container>
    </>
  );
}
