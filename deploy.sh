#!/bin/bash

IMAGE_NAME="smartform"

echo "Building the project..."
./gradlew clean build

if [ $? -ne 0 ]; then
    echo "Build failed. Exiting..."
    exit 1
fi

echo "Building Docker image..."
docker build -t $IMAGE_NAME .

if [ $? -ne 0 ]; then
    echo "Docker image build failed. Exiting..."
    exit 1
fi

echo "Starting Docker Compose..."
docker-compose up -d --build --force-recreate

# Kontrola, zda Docker Compose byl spuštěn úspěšně
if [ $? -ne 0 ]; then
    echo "Docker Compose failed to start. Exiting..."
    exit 1
fi

echo "Deployment completed successfully!"
