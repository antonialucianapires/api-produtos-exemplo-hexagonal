#!/bin/sh

echo "Creating queue ProducReceivedQueue..."

aws --endpoint="http://localhost:4566" sqs create-queue --queue-name=ProducReceivedQueue

aws --endpoint="http://localhost:4566" sqs create-queue --queue-name=DistributedProductsQueue

echo "Ok!"