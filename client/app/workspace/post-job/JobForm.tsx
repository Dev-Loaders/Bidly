"use client"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import axios from 'axios'
import { useSession } from 'next-auth/react'


type JobFormDataProps = {
  title: string;
  location: string;
  image: File | null;
  materials: boolean;
  description: string;
}

export const JobForm = () => {
  const parseJwt = (token) => {
    try {
      return JSON.parse(atob(token.split('.')[1]));
    } catch (e) {
      console.log(e);
      return null;
    }
  }

  const [title, setTitle] = useState('');
  const [location, setLocation] = useState('');
  const [image, setImage] = useState<File | null>(null);
  const [materials, setMaterials] = useState(false);
  const [description, setDescription] = useState('');

  const handleTitle = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  }

  const handleLocation = (event: React.ChangeEvent<HTMLInputElement>) => {
    setLocation(event.target.value);
  }

  const handleImage = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
        setImage(event.target.files[0]);
    }
  }

  const handleMaterials = (event: React.ChangeEvent<HTMLInputElement>) => {
    setMaterials(event.target.checked);
  }

  const handleDescription = (event: React.ChangeEvent<HTMLInputElement>) => {
    setDescription(event.target.value);
  }

  const handleSubmit = () => {
    postJob({title: title, location: location, image: image, materials: materials, description: description})
  }

  const postJob = ({title, location, image, materials, description}: JobFormDataProps) => {
    const formData = new FormData();

    if (image) {
      formData.append('image', image);
    }
    formData.append('title', title);
    formData.append('location', location);
    formData.append('materials', String(materials));
    formData.append('description', description);

    axios.post('https://bidly-app.azurewebsites.net/api/users/' + userId + "/jobs", formData, {
      headers: {'Content-Type': 'multipart/form-data'}
    })
    .then(response => {
      console.log(response);
    })
    .catch((exception) => console.error(exception));
  }

    return (
      <>
        <Form className="p-5 rounded shadow" style={{backgroundColor: "#f8f9fa"}} method="post">
          <h2 className="mb-3 text-center">Create a Job</h2>
          <hr />
          <Form.Group className="mb-4" >
            <Form.Label htmlFor="title">Title</Form.Label>
            <Form.Control onChange={handleTitle} type="text" name="title" placeholder="Enter a Job Title" required /> 
          </Form.Group>

          <Form.Group className="mb-4" >
            <Form.Label htmlFor="location">Location</Form.Label>
            <Form.Control onChange={handleLocation} type="text" name="location" placeholder="Enter Location" required /> 
          </Form.Group>

          <Form.Group className="mb-4" >
            <Form.Label htmlFor="image">Upload image</Form.Label>
            <Form.Control onChange={handleImage} name="image" type="file" /> 
          </Form.Group>

          <Form.Group className="mb-4">
            <Form.Check
              inline
              label="Materials Provided"
              name="materials"
              type="checkbox"
              id="inline-checkbox-1"
              onChange={handleMaterials}
            />
          </Form.Group>

          <Form.Group className="mb-4" >
            <Form.Label htmlFor="description">Description</Form.Label>
            <Form.Control onChange={handleDescription} type="text" name="description" as="textarea" rows={3} placeholder="Enter a Description of the Job" required />
          </Form.Group>

          <Button variant="primary" type="submit" className="w-100" onClick={handleSubmit}>
            Submit
          </Button>
        </Form>
      </>
    );
}