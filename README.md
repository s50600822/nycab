# Simple Cab
This sample webapp demo a single GET endpoint, which list the amount of trips each cab made on any particular day:

    /trip?cabIds=<CAB_IDS>
    /trip?cabIds=<CAB_IDS>&purgeCache=true

For example:

    curl http://localhost:8080/trip\?cabIds\=BBD41D16BDEE492B03A57F646424DD67,5AB4DE718E958FC082557F03BF439493
    {"5AB4DE718E958FC082557F03BF439493":{"2013-12-30":5,"2013-12-31":14,"2013-12-01":1,"2013-12-03":1},"BBD41D16BDEE49.    2B03A57F646424DD67":{"2013-12-30":41,"2013-12-31":35,"2013-12-03":4,"2013-12-06":6}}    
- CAB_IDS can be one or a comma separated list of IDs
- purgeCache when specified will evict all cache before getting the data. Cache configs can be tuned in ./src/main/resources/application.properties.

# Notes
  The app supposed to provide API to query cabs trip count on any given date via /trip. However /trip is not complete as I am looking to:
  - translate the SQL(mentioned in the last section) into org.springframework.data.jpa.repository.Query. Or swith to using other queryDSL that is more flexible. JPA query seems to have quite a few limit include function inside aggregate functions(for example COUNT(DATE_FORMAT(pickup_datetime, '%Y-%m-%d') ends up invalid ) ). Should probably just use jdbc template...
  - add params for the request.
  - add caching as well as param for cache eviction.
  - tests.
  - put it into a container for deployment.
  - performance, indexing?

# BUILD + test the app:
    mvn clean install -DskipTestss
This will produce ./target/simplecab-0.0.1-SNAPSHOT.jar, to run the test make sure database is ready in the background(see below Docker Container for cab database).

# RUN the app:
Note that "Docker Container for cab database" needs to be running for the app to start.
## from built artifact:
    java -jar  ./target/simplecab-0.0.1-SNAPSHOT.jar
## from source:
    mvn spring-boot:run
## from deployed container
    (comming soon)

# Docker Container for cab database:
Before starting the app, we need to start the MySQL container with populated data. This will be provided by the following [image](https://cloud.docker.com/u/s50600822/repository/docker/s50600822/mysql_simplecab). See definition in [Dockerfile](https://github.com/s50600822/nycab/blob/master/scripts/Dockerfile):

    docker build . -t s50600822/mysql_simplecab

This container when up:

    docker run -p 3306:3306  s50600822/mysql_simplecab:7
should already have ny_cab_data database created with cab_trip_data table populated with data, ready to accept remote connection.

# DATABASE

    mysql> describe cab_trip_data;
    +--------------------+----------+------+-----+---------+-------+
    | Field              | Type     | Null | Key | Default | Extra |
    +--------------------+----------+------+-----+---------+-------+
    | medallion          | text     | YES  |     | NULL    |       |
    | hack_license       | text     | YES  |     | NULL    |       |
    | vendor_id          | text     | YES  |     | NULL    |       |
    | rate_code          | int(11)  | YES  |     | NULL    |       |
    | store_and_fwd_flag | text     | YES  |     | NULL    |       |
    | pickup_datetime    | datetime | YES  |     | NULL    |       |
    | dropoff_datetime   | datetime | YES  |     | NULL    |       |
    | passenger_count    | int(11)  | YES  |     | NULL    |       |
    | trip_time_in_secs  | int(11)  | YES  |     | NULL    |       |
    | trip_distance      | double   | YES  |     | NULL    |       |
    | pickup_longitude   | double   | YES  |     | NULL    |       |
    | pickup_latitude    | double   | YES  |     | NULL    |       |
    | dropoff_longitude  | double   | YES  |     | NULL    |       |
    | dropoff_latitude   | double   | YES  |     | NULL    |       |
    +--------------------+----------+------+-----+---------+-------+
    14 rows in set (0.00 sec)

## To query cab trip count on given date
### ALL
    select distinct(medallion), DATE_FORMAT(pickup_datetime, '%Y-%m-%d'), count(DATE_FORMAT(pickup_datetime, '%Y-%m-%d')) from cab_trip_data group by medallion, DATE_FORMAT(pickup_datetime, '%Y-%m-%d')

### GIVEN medallions/cabs
    select medallion, DATE_FORMAT(pickup_datetime, '%Y-%m-%d'), count(DATE_FORMAT(pickup_    datetime, '%Y-%m-%d')) from cab_trip_data  where medallion in     ('A0B5AF0F9B31690CEBB51ECD27D2BE71', '5AB4DE718E958FC082557F03BF439493') group by     medallion, DATE_FORMAT(pickup_datetime, '%Y-%m-%d');
- The way we query data, this table might not be perfect, MySQL doesn't seem to support composite index of medallion and DATE_FORMAT(pickup_datetime, '%Y-%m-%d'). It might be better to add a GENERATED date col with only date and declare index on the two for optimization.
