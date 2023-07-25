"use client"
import { useSearchParams } from 'next/navigation';
import DefaultWorkspace from "./DefaultWorkspace";
import { useCookies } from "react-cookie";


export default function Workspace() {
  
  const searchParams = useSearchParams();
  const token = searchParams.get('token');

  const [cookies, setCookie] = useCookies(['token']);

  const setNewToken = (token: any) => {
    console.log(token);
    console.log(cookies);
    setCookie('token', token, {
      path: '/',
    });
  };

  setNewToken(token);
  
  return (
    <>
      <DefaultWorkspace token={token}></DefaultWorkspace>
    </>
  );
}
