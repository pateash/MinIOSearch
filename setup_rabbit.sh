echo "Creating vhost..."
rabbitmqctl add_vhost my_vhost

echo "Setting permissions..."
rabbitmqctl set_permissions -p my_vhost user ".*" ".*" ".*"

echo "Creating exchange..."
rabbitmqadmin -u user -p secret -V my_vhost declare exchange name=minioExchange type=direct

echo "Creating queue..."
rabbitmqadmin -u user -p secret -V my_vhost declare queue name=minioQueue durable=true

echo "Binding queue to exchange..."
rabbitmqadmin -u user -p secret -V my_vhost declare binding source=minioExchange destination_type=queue destination=minioQueue routing_key=minio
