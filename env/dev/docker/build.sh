#!/usr/bin/env bash

IMAGE=kg.biom/justice
VERSION=dev

docker build \
-t ${IMAGE}:${VERSION} \
-f ./env/dev/docker/Dockerfile .
