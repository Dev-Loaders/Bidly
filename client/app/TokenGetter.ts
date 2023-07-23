import { redirect } from "react-router-dom";
import jwtDecode from "jwt-decode";

type decodedToken = {
    sub: string;
    iat: number;
    exp: number;
    iss: string;
};

type cookies = {
    tokenCookie: decodedToken;
};

export const getUserSubjectFromCookie= (cookies : cookies) => {

  if (cookies.tokenCookie === undefined) {
    window.location.href = "/";
    return;
  }

    const tokenValue = cookies.tokenCookie;
    console.log(tokenValue);
    const decodedToken: decodedToken = jwtDecode(tokenValue);
    console.log(decodedToken);
    console.log(decodedToken.sub);
    return decodedToken.sub;
};