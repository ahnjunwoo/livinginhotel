#!/bin/bash

case $1 in
  replica)
    mysql_host="mysql-replica"
    ;;
  *)
    mysql_host="mysql-main"
    ;;
esac

container_id=$(docker-compose exec ${mysql_host} hostname | tr -d '\r')
docker-compose exec ${mysql_host} tail -f /var/lib/mysql/${container_id}-slow.log
