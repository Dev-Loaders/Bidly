import Image from 'next/image'

export default async function Home() {

  return (
    <main>
      <h1>Welcome to Bidly</h1>
      <a href={`https://bidly-app.azurewebsites.net/login`} type="button">Create Account</a>
    </main>
  )
}
