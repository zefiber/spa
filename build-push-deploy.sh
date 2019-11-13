#!/bin/bash
version="$1"

if [ -z "$1"] ;then
  mvn --batch-mode release:update-versions
  mvn -DskipTests clean package dockerfile:build
  export version=$(mvn help:evaluate -Dexpression=project.version | grep -e '^[^\[]')
fi

if [ "$version" = "" ] ;then
  version="0.1.0"
fi

echo zefiber/spa



docker tag spa:$version zefiber/spa


