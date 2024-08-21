"use client"

import React from "react";
import { BrowserRouter as Router, Route, RouterProvider, createBrowserRouter } from "react-router-dom";
import ElectionPage from "./election-page";
import IdentifyPage from "./identify-user-form";
import VotingPage from "./candidate-selection"; // Assuming you have VotingPage component

const router = createBrowserRouter([
  {
    path: "/",
    element: <IdentifyPage></IdentifyPage>,
  },
  {
    path: "/events",
    element: <ElectionPage></ElectionPage>,
  },
  {
    path: "/vote/:electionId",
    element: <VotingPage></VotingPage>,
  }
]);
export default function Home() {
  return (
    // <main className="flex min-h-screen flex-col items-center justify-between p-24">
    //   {/* <IdentifyUserForm></IdentifyUserForm> */}
    //   <VotingPage></VotingPage>
    // </main>
    <RouterProvider router={router} />

  );
}
