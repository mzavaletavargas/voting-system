"use client"
import React, { useState, useEffect } from "react";

import axios from "axios";
import "./styles.css"; // Ensure to create and import this CSS file for styles
import http from "../../../axios";

function VotingPage({params}: any) {
  const [candidates, setCandidates] = useState([]);
  const [selectedCandidate, setSelectedCandidate] = useState('');

  useEffect(() => {
    // Fetch candidates from the API
    http.get(`/election-events/candidates/${params.slug}`)
      .then(response => {
        setCandidates(response.data);
      })
      .catch(error => {
        console.error("There was an error fetching the candidates!", error);
      });
  }, []);

  const handleCandidateSelect = (id: string) => {
    setSelectedCandidate(id);
  };

  const handleConfirmVote = () => {
    if (selectedCandidate !== null) {
      http.post('/ballots', {
        candidateId: selectedCandidate,
        electionEvent: params.slug
      })
    } else {
      alert("Please select a candidate before confirming your vote.");
    }
  };

  return (
    <div className="max-w-lg mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg">
      <h2 className="text-2xl font-bold mb-6 text-center">Vote for a Candidate</h2>
      <div className="space-y-6">
        {candidates.map((candidate: any) => (
          <div
            key={candidate.id}
            className={`flex items-center justify-between p-4 border rounded-lg ${selectedCandidate === candidate.id ? "border-indigo-600" : "border-gray-300"}`}
            onClick={() => handleCandidateSelect(candidate.id)}
          >
            <div className="w-1/3">
              <p className="font-medium text-gray-700">{candidate.party}</p>
              <p className="text-sm text-gray-500">{candidate.name}</p>
            </div>
            <div className="w-1/3 flex items-center justify-center">
              <img
                src={candidate.photo || 'https://placehold.co/400x400'}
                alt={`${candidate.name}`}
                className="w-16 h-16 rounded-lg object-cover"
              />
            </div>
            <div className="w-1/3 relative">
              <img
                src={candidate.logo || 'https://placehold.co/400x400'}
                alt={`${candidate.party} Logo`}
                className={`w-16 h-16 object-cover ${selectedCandidate === candidate.id ? "crossed" : ""}`}
              />
              {selectedCandidate === candidate.id && (
                <div className="crossmark"></div>
              )}
            </div>
          </div>
        ))}
      </div>
      <button
        onClick={handleConfirmVote}
        className="mt-6 w-full px-4 py-2 bg-indigo-600 text-white font-medium rounded-md shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2"
      >
        Confirm Vote
      </button>
    </div>
  );
}

export default VotingPage;
