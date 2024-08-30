'use client'
import React, { useState, useEffect, useRef } from 'react'
import './styles.css' 
import * as StompJs from '@stomp/stompjs';

function ElectionEventResults() {
  const [results, setResults] = useState([])
  const [totalVoters, setTotalVoters] = useState(0)
  const [totalVotes, setTotalVotes] = useState(0)
  const [blankVotes, setBlankVotes] = useState(0)
  const stompClientRef = useRef(null);

  useEffect(() => {

    const data = {
      totalVoters: 0,
      totalVotes: 0,
      blankVotes: 0,
      candidates: [
       
      ]
    }
    setResults(data.candidates)
    setTotalVoters(data.totalVoters)
    setTotalVotes(data.totalVotes)
    setBlankVotes(data.blankVotes)

    const client = new StompJs.Client({
     brokerURL: 'ws://127.0.0.1:8081/gs-guide-websocket',
     onConnect: () => {
       console.log('Connected to WebSocket');
       client.subscribe(`/topic/results`, (message) => {
         const updatedData = JSON.parse(message.body);
         console.log('Received updated results:', updatedData);
         setResults(updatedData.candidates);
         setTotalVoters(updatedData.totalVoters);
         setTotalVotes(updatedData.totalVotes);
         setBlankVotes(updatedData.blankVotes);
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
  }, [setTotalVoters, setBlankVotes, setResults])

  const calculatePercentage = (votes) => {
    return ((votes / totalVoters || 1) * 100).toFixed(2)
  }

  return (
    <div className="max-w-lg mx-auto mt-10 p-6 bg-white rounded-lg shadow-lg text-black">
      <h2 className="text-2xl font-bold mb-6 text-center text-black">
        Election Results
      </h2>
      <p>Total persons in the election: {totalVoters}</p>
      <p>Total voters: {totalVotes}</p>
      <p>
        Blank votes: {blankVotes} ({calculatePercentage(blankVotes)}%)
      </p>
      <div className="space-y-6 mt-6">
        {results.map((result) => (
          <div
            key={result.candidateId}
            className="p-4 border rounded-lg bg-white"
          >
            <p className="font-medium text-gray-700">
              {result.candidateName} from {result.partyName}
            </p>
            <p className="text-sm text-gray-500">Votes: {result.votes}</p>
            <div className="w-full bg-gray-200 rounded-full h-4 mb-4">
              <div
                className="bg-blue-500 h-4 rounded-full"
                style={{ width: `${calculatePercentage(result.votes)}%` }}
              ></div>
            </div>
            <p className="text-sm text-gray-500">
              {calculatePercentage(result.votes)}%
            </p>
          </div>
        ))}
      </div>
      <div className="mt-6 p-4 border rounded-lg bg-white">
        <p className="font-medium text-gray-700">Missing votes</p>
        <div className="w-full bg-gray-200 rounded-full h-4 mb-4">
          <div
            className="bg-yellow-500 h-4 rounded-full"
            style={{
              width: `${calculatePercentage(
                totalVoters - totalVotes - blankVotes
              )}%`
            }}
          ></div>
        </div>
        <p className="text-sm text-gray-500">
          {calculatePercentage(totalVoters - totalVotes - blankVotes)}%
        </p>
      </div>
    </div>
  )
}

export default ElectionEventResults
