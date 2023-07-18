"use client"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

export const JobForm = (props: {
    children: React.ReactNode
  }) => {
    return (
      <>
        <Form className="p-5 rounded shadow" style={{backgroundColor: "#f8f9fa"}}>
          <h2 className="mb-3 text-center">Create a Job</h2>
          <hr />
          <Form.Group className="mb-4" >
            <Form.Label>Title</Form.Label>
            <Form.Control type="text" placeholder="Enter a Job Title" /> 
          </Form.Group>

          <Form.Group className="mb-4" >
            <Form.Label>Location</Form.Label>
            <Form.Control type="text" placeholder="Enter Location" /> 
          </Form.Group>

          <Form.Group className="mb-4" >
            <Form.Label>Upload image</Form.Label>
            <Form.Control type="file" /> 
          </Form.Group>

          <Form.Group className="mb-4">
            <Form.Check
              inline
              label="Materials Provided"
              name="group1"
              type="checkbox"
              id="inline-checkbox-1"
            />
          </Form.Group>

          <Form.Group className="mb-4" >
            <Form.Label>Description</Form.Label>
            <Form.Control type="text" as="textarea" rows={3} placeholder="Enter a Description of the Job"/>
          </Form.Group>

          <Button variant="primary" type="submit" className="w-100">
            Submit
          </Button>
        </Form>
      </>
    );
}