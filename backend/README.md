# myenergi-backend

Spring boot application to parse and store data from myenergi-api

Getting data:
* GET /zappi
* GET /zappi/${id}
* GET /zappi/${id}/${year}-${month}-${day}
* GET /zappi/${id}/${year}-${month}-${day}/${year}-${month}-${day}
* 
Persisting data into the repository
* POST /zappi/${id}

## setup

In order to successfully start the application you'll need to provide a few environment specific variables

### environment variables
* SPRING_DATASOURCE_URL
* SPRING_DATASOURCE_USERNAME
* SPRING_DATASOURCE_PASSWORD
* MYENERGI_HUB_SERIAL
* MYENERGI_PASSWORD

### deploying on Render

1. create a postgres database on Render to store your data

3. provide environment variables (see above) that connect to your myenergi hub and your database

4. setup automatic deployment by linking your github fork

5. the image mentioned in the dockerfile will be deployed automatically.

## demo

Application hosted on Render (please mind that the first request may take a while, Render puts my container to sleep when it's not been used for a while)
* [Swagger](https://myenergi-native.onrender.com/swagger-ui.html)
* [Status call](https://myenergi-native.onrender.com/zappi)
* [Zappi '16189184'](https://myenergi-native.onrender.com/zappi/16189184)
* [History 23th of September 2021 for zappi '16189184'](https://myenergi-native.onrender.com/zappi/16189184/2021-09-23)


### [Spring native](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/)

1. Start docker
2. Build an image `mvn spring-boot:build-image -Ddocker-image-name="docker.io/steinv/myenergi:latest" -DskipTests=true` 
3. Push image to registry `docker push docker.io/steinv/myenergi:latest`
4. redeploy application on [Render.com](https://render.com)
