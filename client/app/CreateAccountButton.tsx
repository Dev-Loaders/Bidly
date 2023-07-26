"use client";
import Button from "@mui/material/Button";

export const CreateAccountButton = (props: { children: React.ReactNode }) => {
  return (
    <>
      <Button variant="contained" color="primary">
        {props.children}
      </Button>{" "}
    </>
  );
};
