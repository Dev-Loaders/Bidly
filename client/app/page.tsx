import React from 'react';
import { CreateAccountButton } from './CreateAccountButton';

export default function Home() {

  return (
    <main>
      <h1>Welcome to Bidly</h1>
      <a href={`https://bidly-app.azurewebsites.net/login`}>Create Account</a>
      <CreateAccountButton>this is bootstrap</CreateAccountButton>
    </main>
  )
}
