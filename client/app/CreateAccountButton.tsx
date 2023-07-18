"use client"
import Button from 'react-bootstrap/Button';

export const CreateAccountButton = (props: {
    children: React.ReactNode
  }) => {
    return (
      <>
        <Button variant="primary">{props.children}</Button>{' '}
      </>
    );
}