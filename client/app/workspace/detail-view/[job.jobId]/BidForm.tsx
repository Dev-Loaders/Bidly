import { getUserSubjectFromCookie } from "@/app/TokenGetter";
import axios from "axios";
import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useCookies } from "react-cookie";

declare var window: any;

type BidFormDataProps = {
  amount: string;
};

interface BidFormProps {
    jobId: string;
}

export default function BidForm({ jobId, setNewBid }: BidFormProps & { setNewBid: React.Dispatch<React.SetStateAction<number>>; }) {
  const [amount, setAmount] = useState("");
  const [cookies] = useCookies();

  const handleAmount = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAmount(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    postJob({
      amount: amount,
    });
  };

  const userSubject = getUserSubjectFromCookie(cookies);
  console.log(userSubject);
  console.log(cookies.token);

  const postJob = ({ amount }: BidFormDataProps) => {
    const formData = new FormData();

    formData.append("amount", amount);
    const jobId = window.location.pathname.split("/")[3];

    axios.post(
        "http://localhost:8080/api/users/" + userSubject + "/jobs/" + jobId + "/bids", formData,
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
      <Form
        className="p-5 rounded shadow"
        style={{ backgroundColor: "#f8f9fa" }}
        method="post"
        onSubmit={handleSubmit}
      >
        <h2 className="mb-3 text-center">Bid on Job</h2>
        <hr />
        <Form.Group className="mb-4">
          <Form.Label htmlFor="amount">Bid Amount</Form.Label>
          <Form.Control
            onChange={handleAmount}
            type="text"
            name="amount"
            placeholder="Enter an amount"
            required
          />
        </Form.Group>

        <Button
          variant="primary"
          type="submit"
          className="w-100"
        >
          Submit
        </Button>
      </Form>
    </>
  );
}
