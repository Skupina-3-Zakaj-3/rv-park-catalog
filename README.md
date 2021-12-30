# rv-park-catalog microservice

## Build the image

```bash
docker build -t rv-park-catalog .
```

## Create network for all our microservices

```bash
docker network create rso
```

## Run database in network
```bash
docker run -d --name pg-rv-park-catalog --network="rso" -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=parks -p 5439:5432 postgres:13
```

## Run the container in network

```bash
docker run -d -p 8089:8089 --name rv-park-catalog --network="rso" -e KUMULUZEE_DATASOURCES0_CONNECTIONURL=jdbc:postgresql://pg-rv-park-catalog:5432/parks rv-park-catalog
```

## Run the container from Docker hub in network

```bash
docker run -d -p 8089:8089 --name rv-park-catalog --network="rso" anzeha/rv-park-catalog:latest
```