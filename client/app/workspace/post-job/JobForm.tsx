"use client";
import Button from "react-bootstrap/Button";
import { useState } from "react";
import axios from "axios";
import {
  Box,
  Checkbox,
  FormControl,
  FormControlLabel,
  FormLabel,
  Input,
  InputLabel,
  TextField,
} from "@mui/material";
import { useCookies } from "react-cookie";
import { getUserSubjectFromCookie } from "@/app/TokenGetter";


type JobFormDataProps = {
  title: string;
  location: string;
  image: File | null;
  materials: boolean;
  description: string;
};

export const JobForm = () => {
  const [title, setTitle] = useState("");
  const [location, setLocation] = useState("");
  const [image, setImage] = useState<File | null>(null);
  const [materials, setMaterials] = useState(false);
  const [description, setDescription] = useState("");
  const [cookies] = useCookies();

  const handleTitle = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const handleLocation = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLocation(event.target.value);
  };

  const handleImage = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      setImage(event.target.files[0]);
    }
  };

  const handleMaterials = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMaterials(event.target.checked);
  };

  const handleDescription = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDescription(event.target.value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    postJob({
      title: title,
      location: location,
      image: image,
      materials: materials,
      description: description,
    });
  };

  const userSubject = getUserSubjectFromCookie(cookies);

  const postJob = ({
    title,
    location,
    image,
    materials,
    description,
  }: JobFormDataProps) => {
    const formData = new FormData();

    if (image) {
      formData.append("image", image);
    }
    formData.append("title", title);
    formData.append("location", location);
    formData.append("materials", String(materials));
    formData.append("description", description);

    const userSub = getUserSubjectFromCookie(cookies);

    axios
      .post("http://localhost:8080/api/users/" + userSub + "/jobs", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
          // Authorization: "Bearer " + cookies.token,
        },
      })
      .catch((exception) => console.error(exception));
  };

  return (
    <>
      <Box
        component="form"
        sx={{
          "& .MuiTextField-root": { m: 1, width: "100%", maxWidth: "800px" },
          maxWidth: { xs: "100%", md: "800px" },
          margin: "auto",
          p: { xs: 2, md: 5 }, 
        }}
        autoComplete="off"
        className="rounded shadow"
        style={{ backgroundColor: "#fff", marginBlockStart: "2%" }}
        method="post"
        onSubmit={handleSubmit}
      >
        <h2 className="mb-3 text-center">Publish a Project</h2>
        <hr />

        <TextField
          id="outlined-basic"
          label="Title"
          variant="outlined"
          onChange={handleTitle}
          required
        />

        <TextField
          id="outlined-basic"
          label="Location"
          variant="outlined"
          onChange={handleLocation}
          style={{ marginBottom: "4%" }}
          required
        />

        <FormControl
          component="fieldset"
          className="mb-4"
          fullWidth
          style={{ marginLeft: "2%" }}
        >
          <FormLabel component="legend">Upload image</FormLabel>
          <input
            accept="image/*"
            id="contained-button-file"
            type="file"
            onChange={handleImage}
          />
        </FormControl>

        <FormControlLabel
          control={
            <Checkbox
              checked={materials}
              onChange={handleMaterials}
              name="materials"
            />
          }
          label="Check if you have materials"
          style={{ width: "100%", marginBottom: "4%", marginLeft: "1px" }}
        />

        <TextField
          id="outlined-multiline-static"
          label="Description"
          multiline
          rows={4}
          variant="outlined"
          onChange={handleDescription}
          style={{ marginBottom: "4%" }}
          required
        />

        <Box
          display="flex"
          justifyContent="center"
          marginBottom={2}
          mt={2}
        >
          <Button
            variant="outlined"
            color="inherit"
            type="submit"
            style={{
              border: "1px solid #242424",
              marginBottom: "4%",
              padding: "6px 50px",
            }}
          >
            Submit
          </Button>
        </Box>
      </Box>
    </>
  );
};
