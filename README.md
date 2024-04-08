# MinIOSearchEngine ( RESET all git history )

### Product Requirements Documents (PRD)


### Local Setup
1. Run the Docker Compose file with the following command:

   ```bash
   docker-compose up -d
   ```
   This command starts the containers in the background.

1. Access MinIO:
   - MinIO Console (Web UI) is available at [minio](http://localhost:9001).
   - Use the credentials `minioadmin` for both the username and password (change these in production).
   - documents uploaded throw `API` are available in bucket `docs-bucket`

1. Access Elasticsearch:
   - Elasticsearch can be accessed at [elasticsearch](http://localhost:9200)
   - No authentication is set up for elastic search ( not recommended for Production )


### API Docs
1. Run the application running at `localhost:8080`
1. Swagger UI docs are available at [Swagger docs](http://localhost:8080/swagger-ui/index.html#/)
   * Note - Authentication is enabled for all api end points, use `user` and `secret` 

