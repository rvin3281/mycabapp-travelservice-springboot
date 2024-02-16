package com.atsmart.travelservice.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "driver_id")
	private Integer driverId;
	
	@Column(name = "driver_name", nullable = false)
	private String driverName;
	
	@Column(name = "driver_contact", nullable = false)
	private String driverContact;
	
	@Column(name = "driver_identificationNum", nullable = false)
	private String driverIdentificationNum;
	
	@Column(name = "created_timestamp", nullable = false)
	private Timestamp createdTimestamp;
	
	@Column(name = "update_timestamp", nullable = false)
    private Timestamp updateTimestamp;
    
	@Column(name = "status", nullable = false)
    private int status;
    
    @PrePersist
    protected void onCreate() {
    	createdTimestamp = new Timestamp(System.currentTimeMillis());
    	updateTimestamp = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
        updateTimestamp = new Timestamp(System.currentTimeMillis());
    }
    
    @OneToOne
    @JoinColumn(name = "cab_id", nullable = true)
    private Cab cab;
    
    @OneToMany(mappedBy="driver")
    private List<Travel> travel;
    
}
