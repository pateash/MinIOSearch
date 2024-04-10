VHOST=my_vhost
EXCHANGE=minioExchange
QUEUE=minioQueue

#rabbitmq-plugins enable rabbitmq_prometheus

echo "Creating vhost..."
rabbitmqctl add_vhost $VHOST

echo "Setting permissions..."
rabbitmqctl set_permissions -p $VHOST user ".*" ".*" ".*"

echo "Creating exchange..."
rabbitmqadmin -u user -p secret -V $VHOST delete exchange name=$EXCHANGE
rabbitmqadmin -u user -p secret -V $VHOST declare exchange name=$EXCHANGE type=direct durable=false

echo "Creating queue..."
rabbitmqadmin -u user -p secret -V $VHOST declare queue name=$QUEUE durable=true

echo "Binding queue to exchange..."
rabbitmqadmin -u user -p secret -V $VHOST declare binding source=$EXCHANGE destination_type=queue destination=$QUEUE routing_key=minio
