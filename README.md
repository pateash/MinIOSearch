# MinIOSearch

### Product Requirements Documents (PRD)

Please find prd [here](./docs/PRD.md)

### High level Design (HLD)

Please find the hld [here](./docs/img.png)

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
    - setting `put` the event from MinIO to RabbitMQ using ( run inside MinIO pod )
```bash
mc alias set myminio http://localhost:9000 minioadmin minioadmin
#rabbitmq is service name which will refer to host of rabbigMQ
mc admin config set myminio notify_amqp:1 exchange="myExchange" exchange_type="direct" routing_key="minio" url="amqp://user:secret@rabbitmq:5672" queue_dir="" queue_limit="0" auto_deleted="false" delivery_mode="2"
mc admin service restart myminio
# use your bucket name
mc event add myminio/docs-bucket arn:minio:sqs::1:amqp --event put
```

1. Access Elasticsearch:
    - Elasticsearch can be accessed at [elasticsearch](http://localhost:9200)
    - Index used here is `file-words`

1. Accessing RabbigMQ
   - RabbitMQ UI can be accessed at [rabbigMQUI](http://localhost:15672)
   - docs [here](https://docs.spring.io/spring-boot/docs/current/reference/html/messaging.html#messaging.amqp.rabbitmq)

### API Docs
1. Run the application running at `localhost:8080`
1. Swagger UI docs are available at [Swagger docs](http://localhost:8080/swagger-ui/index.html#/)
   * Note - Authentication is enabled for all api end points, use `user` and `secret` 

