"use client"

import VotingPage from "./candidate-selection";
import IdentifyUserForm from "./identify-user-form";

export default function Home() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
      {/* <IdentifyUserForm></IdentifyUserForm> */}
      <VotingPage></VotingPage>
    </main>
  );
}
