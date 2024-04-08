# MinIOSearchEngine ( RESET all git history )


### Local Setup
1. Run the Docker Compose file with the following command:

   ```bash
   docker-compose up -d
   ```

   This command starts the containers in the background.

1. Access MinIO:
   - MinIO Console (Web UI) is available at `http://localhost:9001`.
   - Use the credentials `minioadmin` for both the username and password (change these in production).

1. Access Elasticsearch:
   - Elasticsearch can be accessed at `http://localhost:9200`
   - No authentication is set up for elastic search ( not recommended for Production )
