package com.atsmart.travelservice.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "travel_id", nullable = false)
	private Integer travelId;
	
	@Column(name = "travel_source", nullable = false)
	private String travelSource;
	
	@Column(name = "travel_destination", nullable = false)
	private String travelDestination;
	
	@Column(name = "travelTime", nullable = false)
	private LocalTime travel_time;
	
	@Column(name = "travel_date", nullable = false)
	private Date travelDate;
	
	@Column(name = "travel_cost", nullable = false)
	private String travelCost;
	
	@Column(name = "created_timestamp", nullable = false)
	private Timestamp createdTimestamp;
	
	@Column(name = "update_timestamp", nullable = false)
    private Timestamp updateTimestamp;
    
	@Column(name = "status", nullable = false)
	private int status;
    
    
    /*
     * The @PrePersist and @PreUpdate annotations are used on methods (onCreate and onUpdate respectively)
     * called automatically before a new entity is persisted (created) and before an existing entity is updated.
     * This way, every time you create a new Travel entity, created_timestamp will be set to the current timestamp
     * every time you update an existing entity, update_timestamp will be updated with the current timestamp.
     * */
    @PrePersist
    protected void onCreate() {
    	createdTimestamp = new Timestamp(System.currentTimeMillis());
    	updateTimestamp = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
    	updateTimestamp = new Timestamp(System.currentTimeMillis());
    }
    
    @ManyToOne
    @JoinColumn(name="driver_id")
    private Driver driver;
    
    @ManyToOne
    @JoinColumn(name="cab_id")
    private Cab cab;
	
}
