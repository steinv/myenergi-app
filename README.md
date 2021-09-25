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

### deploying on heroku

1. If you fork this repository, create a subtree on git to connect
   ```
   git subtree push --prefix backend origin heroku
   ```

2. create a postgres database on heroku to store your data

3. provide environment variables that connect to your myenergi hub

4. setup automatic deployment from the `heroku`-branch

## demo

Application hosted on heroku
* [Swagger](https://myenergi-backend.herokuapp.com/swagger-ui.html)
* [Status call](https://myenergi-backend.herokuapp.com/zappi)
* [Zappi '16189184'](https://myenergi-backend.herokuapp.com/zappi/16189184)
* [History 23th of September 2021 for zappi '16189184'](https://myenergi-backend.herokuapp.com/zappi/16189184/2021/09/23)
