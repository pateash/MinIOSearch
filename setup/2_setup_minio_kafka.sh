MINIO_HOST=http://localhost:9000
KAFKA_BROKERS="broker:9092"
USER=minioadmin
PASSWORD=minioadmin
TOPIC="minio-events"

mc alias set myminio $MINIO_HOST $USER $PASSWORD
mc admin config set myminio notify_kafka:1 brokers="$KAFKA_BROKERS" topic="$TOPIC" sasl_username="" sasl_password="" sasl_mechanism=""
mc admin service restart myminio

# use your bucket name ( delete if already exists )
# remove all events
mc event remove myminio/$BUCKET --force
mc event add myminio/$BUCKET arn:minio:sqs::1:kafka --event put

# checking events
mc event ls myminio/$BUCKET

# checking configs
mc admin config get myminio/ notify_amqp
