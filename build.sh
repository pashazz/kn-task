#!/bin/sh
./gradlew clean build


cd auth
docker build . -t "auth"
cd ..

cd discovery
docker build . -t "discovery"
cd ..

cd gateway
docker build . -t "gateway"
cd ..

cd cities
docker build . -t "cities"
