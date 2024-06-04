#!/bin/bash

cd ..

services=("main-service" "trainer-stats-service")

for service in ${services[@]}; do
  echo "Processing $service"

  cd $service

  ./mvnw -f pom.xml clean package -DskipTests

  docker build -t $service .

  cd ..
done

echo "Done processing all services."
cd integration-tests
echo "Starting services"
docker-compose up -d
echo "Running integration tests"
./mvnw test
docker-compose down
echo "Press any key to continue . . ."
read