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