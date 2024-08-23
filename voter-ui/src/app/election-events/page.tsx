"use client"
import React, { useState, useEffect } from "react";

import "./styles.css"; // Ensure to create and import this CSS file for styles
import { useRouter } from 'next/navigation'
import http from "../../app/axios";

function ElectionEvents() {
  const [elections, setElections] = useState([]);

  useEffect(() => {
    // Fetch elections from the API
    http.get('/election-events')
      .then(response => {
        setElections(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the elections!", error);
      });
  }, []);
  const router = useRouter()

  const handleElectionClick = (id) => {
    router.push(`/election-events/${id}`);
  };

  return (
    <div className="max-w-lg mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg">
      <h2 className="text-2xl font-bold mb-6 text-center">Available Elections</h2>
      <div className="space-y-6">
        {elections.map((election) => (
          <div
            key={election.id}
            className={`p-4 border rounded-lg cursor-pointer ${election.active ? "border-gray-300 bg-white hover:bg-gray-100" : "border-gray-300 bg-gray-200 text-gray-500 cursor-not-allowed"}`}
            onClick={() => election.active && handleElectionClick(election.id)}
          >
            <p className="font-medium text-gray-700">{election.electionName}</p>
            <p className="text-sm text-gray-500">{election.electionType}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ElectionEvents;
