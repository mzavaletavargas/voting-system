# Readme
## Usage

docker-compose up
open  http://localhost:3000/ and participate by using

```
documentId: 76663232
verificationNumber: 1
```

and have open http://localhost:3000/election-events/3caf15f5-c58c-4146-b095-f285c08f5556/results to check websocket changes

Example identify

```
curl --location 'http://localhost:8080/citizen/identify' \
--form 'verificationImage=@"/Users/gzavaleta/Downloads/test1.jpg"' \
--form 'documentId="76663232"' \
--form 'verificationNumber="1"'
```
