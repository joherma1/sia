ARM PostgreSQL version.
Based on the official [dockerfile](https://hub.docker.com/_/postgres/)
* [`9.4`, `9.4.5`, `latest` (9.4/Dockerfile)](https://github.com/joherma1/sia/blob/master/deploy/postgres/Dockerfile)

![empty](http://cdn.shopify.com/s/files/1/0279/1227/t/5/assets/highsnobiety-logo-badge-white.svg?75070636155751373 "empty")

![PostgreSQL](https://raw.githubusercontent.com/docker-library/docs/master/postgres/logo.png "PostgreSQL")
How to use this image start a postgres instance
```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres --name postgres-arm -d postgres
```
![empty](http://cdn.shopify.com/s/files/1/0279/1227/t/5/assets/highsnobiety-logo-badge-white.svg?75070636155751373 "empty")
Usage:
```
docker run -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_SCHEMA=postgres --link postgres:postgres -d you_app
```