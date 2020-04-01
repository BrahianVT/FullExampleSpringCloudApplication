#!/bin/sh
echo "!!!!!!!!! Start Setup vault secrets"

docker-entrypoint.sh server -dev &
sleep 5

vault secrets enable -version=1 kv
vault kv put secret/catalog-service @config/catalog-service.json
vault kv put secret/inventory-service @config/inventory-service.json

echo "!!!!!!!!! End Setup vault secrets"
while true; do sleep 100; done