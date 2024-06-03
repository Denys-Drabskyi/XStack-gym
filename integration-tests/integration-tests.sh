#!/bin/bash

cd ..

services=("main-service" "trainer-stats-service")

for service in ${services[@]}; do
  echo "Processing $service"

  cd $service

  ./mvnw clean package

  docker build -t $service .

  cd ..
done

echo "Done processing all services."
cd integration-tests
echo "Running integration tests"
#./mvnw test

echo "Press any key to continue . . ."
read