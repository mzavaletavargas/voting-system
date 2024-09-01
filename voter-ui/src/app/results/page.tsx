'use client'
import React, { useState, useEffect, useRef } from 'react'
import './styles.css' 
import * as StompJs from '@stomp/stompjs';

function ElectionEventResults() {
  const [candidates, setCandidates] = useState([])
  const [totalVotes, setTotalVotes] = useState(0)
  const stompClientRef = useRef(null);

  useEffect(() => {

    const data = {
      totalVotes: 0,
      candidates: [
       
      ]
    }
    setCandidates(data.candidates)
    setTotalVotes(data.totalVotes)

    const client = new StompJs.Client({
     brokerURL: 'ws://127.0.0.1:8081/gs-guide-websocket',
     onConnect: () => {
       console.log('Connected to WebSocket');
       client.subscribe(`/topic/results`, (message) => {
         const updatedData = JSON.parse(message.body);
         console.log('Received updated results:', updatedData);
         setCandidates(updatedData.candidates);
         setTotalVotes(updatedData.totalVotes);
       });
     },
     onStompError: (frame) => {
       console.error('Broker reported error:', frame.headers['message']);
       console.error('Additional details:', frame.body);
     },
     onWebSocketError: (error) => {
       console.error('Error with WebSocket', error);
     }
   });

   client.activate();
   stompClientRef.current = client;

   // Clean up on component unmount
   return () => {
     if (stompClientRef.current) {
       stompClientRef.current.deactivate();
     }
   };
  }, [setTotalVotes, setCandidates])

  const calculatePercentage = (votes: number) => {
    return ((votes / totalVotes || 1) * 100).toFixed(2)
  }
  console.log(candidates);
  return (
    <div className="max-w-lg mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg text-black">
      <h2 className="text-2xl font-bold mb-6 text-center text-black">
        Election Results
      </h2>
      <p>Total voters: {totalVotes}</p>

      <div className="space-y-6 mt-6">
        {candidates.map((candidate) => (
          <div
            key={candidate.id}
            className="p-4 border rounded-lg bg-white"
          >
            <p className="font-medium text-gray-700">
              {candidate.name} from {candidate.party}
            </p>
            <p className="text-sm text-gray-500">Votes: {candidate.votes}</p>
            <div className="w-full bg-gray-200 rounded-full h-4 mb-4">
              <div
                className="bg-blue-500 h-4 rounded-full"
                style={{ width: `${calculatePercentage(candidate.votes)}%` }}
              ></div>
            </div>
            <p className="text-sm text-gray-500">
              {calculatePercentage(candidate.votes)}%
            </p>
          </div>
        ))}
      </div>

    </div>
  )
}

export default ElectionEventResults
