package com.example.cab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author hoaphan
 * +--------------------+----------+------+-----+---------+-------+
 * | Field              | Type     | Null | Key | Default | Extra |
 * +--------------------+----------+------+-----+---------+-------+
 * | medallion          | text     | YES  |     | NULL    |       |
 * | hack_license       | text     | YES  |     | NULL    |       |
 * | vendor_id          | text     | YES  |     | NULL    |       |
 * | rate_code          | int(11)  | YES  |     | NULL    |       |
 * | store_and_fwd_flag | text     | YES  |     | NULL    |       |
 * | pickup_datetime    | datetime | YES  |     | NULL    |       |
 * | dropoff_datetime   | datetime | YES  |     | NULL    |       |
 * | passenger_count    | int(11)  | YES  |     | NULL    |       |
 * | trip_time_in_secs  | int(11)  | YES  |     | NULL    |       |
 * | trip_distance      | double   | YES  |     | NULL    |       |
 * | pickup_longitude   | double   | YES  |     | NULL    |       |
 * | pickup_latitude    | double   | YES  |     | NULL    |       |
 * | dropoff_longitude  | double   | YES  |     | NULL    |       |
 * | dropoff_latitude   | double   | YES  |     | NULL    |       |
 * +--------------------+----------+------+-----+---------+-------+
 */
@Entity
@Table(name="cab_trip_data")
public class Trip {
//    @Id
//    @Column(name = "id", insertable = false, updatable = false)
//    private String id;

	@Id //Not an ID, not unique
    @Column(name = "medallion")
	private String medallion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "pickup_datetime")
	private java.util.Date pickup_datetime;
}
