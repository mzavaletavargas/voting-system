"use client"
import React, { useState, useEffect } from "react";

import "./styles.css"; // Ensure to create and import this CSS file for styles
import { useRouter } from 'next/navigation'
import http from "../app/axios";

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

  const router = useRouter();

  const handleParticipateClick = (id: string) => {
    router.push(`/election-events/${id}/participate`);
  };

  const handleResultsClick = (id: string) => {
    router.push(`/election-events/${id}/results`);
  };

  return (
    <div className="max-w-lg mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg">
      <h2 className="text-2xl font-bold mb-6 text-center">Available Elections</h2>
      <div className="space-y-6">
        {elections.map((election: any) => (
          <div
            key={election.id}
            className={`p-4 border rounded-lg ${election.active ? "border-gray-300 bg-white" : "border-gray-300 bg-gray-200 text-gray-500 cursor-not-allowed"}`}
          >
            <p className="font-medium text-gray-700">{election.electionName}</p>
            <p className="text-sm text-gray-500">{election.electionType}</p>
            <div className="flex justify-end space-x-4 mt-4">
              <button
                className={`px-4 py-2 rounded ${election.active ? "bg-blue-500 text-white hover:bg-blue-700" : "bg-gray-400 text-gray-700 cursor-not-allowed"}`}
                onClick={() => election.active && handleParticipateClick(election.id)}
                disabled={!election.active}
              >
                Participate
              </button>
              <button
                className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-700"
                onClick={() => handleResultsClick(election.id)}
              >
                Check Results
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ElectionEvents;
