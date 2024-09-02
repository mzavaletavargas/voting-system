# Use an official Node.js image as the base
FROM node:18-alpine

# Set the working directory inside the container
WORKDIR /app

# Install dependencies
COPY package.json package-lock.json ./
RUN npm install --frozen-lockfile

# Copy only the necessary files to the container
COPY . .


# Build the Next.js application
RUN yarn build

# Expose the port on which the Next.js app runs
EXPOSE 3000

# Start the application in development mode
CMD ["yarn", "dev"]
