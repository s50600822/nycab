#BUILD Docker Container
    docker build . -t s50600822/mysql_simplecab

#DATA

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
