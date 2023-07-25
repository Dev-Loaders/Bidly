"use client"
import { useSearchParams } from 'next/navigation';
import DefaultWorkspace from "./DefaultWorkspace";
import { useCookies } from "react-cookie";


export default function Workspace() {
  
  const searchParams = useSearchParams();
  const token = searchParams.get('token');

  const [cookies, setCookie] = useCookies(['token']);
  const setTokenAsCookie = (token: any) => {
    setCookie('token', token, { path: '/'});
  };
  setTokenAsCookie(token);
  console.log(cookies.token);

  
  return (
    <>
      <DefaultWorkspace token={token}></DefaultWorkspace>
    </>
  );
}
