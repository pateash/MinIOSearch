# MinIOSearch

### Product Requirements Documents (PRD)

Please find prd [here](./docs/PRD.md)

### High level Design (HLD)
![img](./docs/hld.png)

### Local Setup

1. Run the Docker Compose file with the following command:

   ```bash
   docker-compose up -d
   ```
   This command starts the containers in the background.
1. Accessing RabbigMQ
   - RabbitMQ UI can be accessed at [rabbigMQUI](http://localhost:15672)
   - docs [here](https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging.amqp.rabbitmq)
   - creating exchange, vhost and queue in RabbitMQ for MinIO
```text
Run content of  [file](./setup_rabbit.sh) inside rabbitMQ pod
```

1. Access MinIO:
    - MinIO Console (Web UI) is available at [minio](http://localhost:9001).
    - Use the credentials `minioadmin` for both the username and password (change these in production).
    - documents uploaded throw `API` are available in bucket `docs-bucket`
    - setting `put` the event from MinIO to RabbitMQ using ( run inside MinIO pod )
```text
Run content of  [file](./setup_minio.sh) inside minIO pod
```
1. Access Elasticsearch:
    - Elasticsearch can be accessed at [elasticsearch](http://localhost:9200)
    - Index used here is `file-words`

### API Docs
1. Run the application running at `localhost:8080`
1. Swagger UI docs are available at [Swagger docs](http://localhost:8080/swagger-ui/index.html#/)
   * Note - Authentication is enabled for all api end points, use `user` and `secret` 

