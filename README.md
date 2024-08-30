# Readme

his application enables secure and verified voting in a presidential election event. It allows users to authenticate themselves using their document ID, the identification number on the document, and a photo. The photo is processed to ensure that the person voting is indeed the individual associated with the provided credentials, thereby preventing voter fraud. Each user is permitted to vote only once for a candidate in the election.

Key Features:

1. User Authentication:
   • Document ID & Identification Number: Users must provide both their document ID and the identification number associated with that document.
   • Photo Verification: A photo of the user is taken and processed to confirm their identity, ensuring that the person voting is the same as the one associated with the provided credentials.
2. One Vote Per User:
   • The system is designed to ensure that each authenticated user can cast only one vote for a candidate in the presidential election.
3. Real-Time Election Statistics:
   • The application publicly displays real-time statistics and results of the election event. This feature provides transparency and allows users to see the current standings of candidates as votes are cast.

## Pre-requisites

- docker
- java

## folders

playground: test java stuff
voting-service: identify a user and make possible to participate in the voting application
voter-ui: it's the FE that will be render and communicate to the system
stream-service: will feed data to the public (real time resuls of the election in process)

## Todo

- include lombok
- improve voter-ui
- send data from voting-service to stream-service
- stream-service should serve data to a public UI
- separate in a different service image processing to validate user and notify the user-service when it's done
- separate user authentification to a new service (user-service)
- add kafka to communicate and change communication to be an event-driven design
- add integration tests
- update the project to work with k8s

## How to run

voter-ui: yarn dev
voting-service: run the app with graddle

![](/resources/demo1.mov)

<video style="width:70%" controls src="resources/demo1.mov"></video>
