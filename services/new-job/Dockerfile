# Use a base image that has curl installed
FROM alpine:latest

# Install curl
RUN apk add --no-cache curl

# Default command to run curl
#CMD ["curl", "127.0.0.1:8080/jobs", "-d{'status':'COMPLETED'}", "-HContent-Type: application/json", "-v"]
CMD ["curl", "127.0.0.1:8080/jobs", "-d", "{\"status\":\"COMPLETED\"}", "-HContent-Type: application/json", "-v"]

