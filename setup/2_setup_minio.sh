MINIO_HOST=http://localhost:9000
USER=minioadmin
PASSWORD=minioadmin
VHOST=my_vhost
EXCHANGE=minioExchange
QUEUE=minioQueue
BUCKET=docs-bucket
RQ_USER=user
RQ_PASSWORD=secret
URL="amqp://$RQ_USER:$RQ_PASSWORD@rabbitmq:5672/$VHOST"

mc alias set myminio $MINIO_HOST $USER $PASSWORD

#rabbitmq is service name which will refer to host of rabbigMQ
mc admin config set myminio notify_amqp:1 exchange="$EXCHANGE" exchange_type="direct" routing_key="minio" url="$URL" queue_dir="" queue_limit="0" auto_deleted="false" durable="false" delivery_mode="2"
mc admin service restart myminio


# use your bucket name ( delete if already exists )
# remove all events
mc event remove myminio/$BUCKET --force

#mc event add myminio/$BUCKET arn:minio:sqs::1:amqp --event put
# adding multiple at once
mc event add myminio/$BUCKET arn:minio:sqs::1:amqp --event put,get,delete
#--prefix "logs/"


# checking events
mc event ls myminio/$BUCKET

# checking configs
mc admin config get myminio/ notify_amqp
