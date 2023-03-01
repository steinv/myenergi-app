# myenergi-backend

Spring boot application to parse and store data from myenergi-api

Getting data:
* `GET /zappi`
* `GET /zappi/${id}`
* `GET /zappi/${id}/${year}-${month}-${day}`
* `GET /zappi/${id}/${year}-${month}-${day}/${year}-${month}-${day}`

Persisting data into the repository
* `POST /zappi/${id}`

## setup

### Building [Spring native](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/) image

1. Start docker
2. Build an image `mvn spring-boot:build-image -Ddocker-image-name="docker.io/steinv/myenergi:latest" -DskipTests=true` 
3. Push image to registry `docker push docker.io/steinv/myenergi:latest`


### Database

1. create a postgres database to store your data
`flyctl pg create -n myenergi-db`

2. connections from external locations need to be proxied

`flyctl proxy 5432 -a myenergi-db`

3. create table (flyway is not implemented yet)

```
CREATE TABLE public.history (
	"date" date NOT NULL,
	serial varchar(255) NOT NULL,
	charged int4 NOT NULL,
	consumed int4 NOT NULL,
	exported int4 NOT NULL,
	"generated" int4 NOT NULL,
	history bytea NULL,
	imported int4 NOT NULL,
	CONSTRAINT history_pkey PRIMARY KEY (date, serial)
);
```

ps: the table was originally designed for heroku where there's a limit of how many records you can freely store.
You'll notice a lot of data is available in byte column called "history". This should be refactored in the future. 

### deploying on fly.io

1. create a fly.toml
`flyctl launch`

2. provide environment variables in fly.toml and secrets through cli that connect to your myenergi hub and your database

```
[env]
  SPRING_DATASOURCE_URL="jdbc:postgresql://myenergi-db.internal:5432/postgres"
  ...
```

or secrets:
```
flyctl secrets set SPRING_DATASOURCE_USERNAME=xxxxx
flyctl secrets set SPRING_DATASOURCE_PASSWORD=xxxxx
flyctl secrets set MYENERGI_HUB_SERIAL=xxxxx
flyctl secrets set MYENERGI_PASSWORD=xxxxx
```

3. Run a deployment
`flyctl deploy`

## demo

Application is hosted on Fly.io (please mind that the first request may take a while, Fly.io puts my container to sleep when it's not been used for a while)
* [Swagger](https://myenergi-native.fly.dev/swagger-ui.html)
* [Status call](https://myenergi-native.fly.dev/zappi)
* [Zappi '16189184'](https://myenergi-native.fly.dev/zappi/16189184)
* [History 23th of September 2021 for zappi '16189184'](https://myenergi-native.fly.dev/zappi/16189184/2021-09-23)


## Troubleshooting

Connection issues to the db could be resolved by restarting the fly.io machine

```
 fly machines list -a myenergi-db
 fly machines restart 9080173a629138 -a myenergi-db
```







