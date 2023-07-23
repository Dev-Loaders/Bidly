"use client"
// import { cookies } from 'next/headers'

// export function TokenCookie() {
//     const cookieStore = cookies()
//     console.log(cookieStore),
//     console.log("New")

//   return (

//     <div>
//         <p>Sometjing</p>
//     </div>
//   )
// }

import { useCookies } from "react-cookie";

// export const TokenCookie = () => {
//   const [cookieStore, setCookieStore] = useCookies(["tokenCookie"]);

//   console.log(cookieStore);
//   console.log("New");

//   return (
//     <div>
//       <p>Something</p>
//       <p>{cookieStore.tokenCookie}</p>
//     </div>
//   );
// };

// export default TokenCookie;

export const TokenCookie = () => {
  const [cookies] = useCookies();

  console.log(cookies);
};

// export default TokenCookie;