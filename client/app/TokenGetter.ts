import { redirect } from "react-router-dom";
import jwtDecode from "jwt-decode";

type decodedToken = {
    sub: string;
};

type jwtCookie = {
    tokenCookie: decodedToken;
};

export const getUserSubjectFromCookie = (cookies : jwtCookie) => {

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