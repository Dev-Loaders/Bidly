import Image from 'next/image'

const getHello = async () => {
  const response = await fetch('https://bidly-app.azurewebsites.net/api/hello');
  if (!response.ok) {
    throw new Error("Could not fetch");
  }
  const text = await response.text();
  return text;
}

const getUser = async () => {
  const response = await fetch('https://bidly-app.azurewebsites.net/api/user');
  if (!response.ok) {
    throw new Error("Could not fetch");
  }
  const json = await response.json();
  return json;
}

export default async function Home() {
  const str = await getHello();
  const user = await getUser();

  return (
    <main>
      <div>{str}</div>
      <div>{user}</div>
    </main>
  )
}
