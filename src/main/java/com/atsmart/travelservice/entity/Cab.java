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
public class Cab {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "cab_id", nullable = false)
	private Integer cabId;
	
	@Column(name = "cab_model", nullable = false)
	private String cabModel;
	
	@Column(name = "cab_name", nullable = false)
	private String cabName;
	
	@Column(name = "cab_plateNum", nullable = false)
	private String cabPlateNum;
	
	@Column(name = "created_timestamp", nullable = false)
	private Timestamp createdTimestamp;
	
	@Column(name = "update_timestamp", nullable = false)
    private Timestamp updateTimestamp;
	
	@Column(name = "status", nullable = false)
    private int Status;
    
    @PrePersist
    protected void onCreate() {
    	createdTimestamp = new Timestamp(System.currentTimeMillis());
    	updateTimestamp = new Timestamp(System.currentTimeMillis());
    }
    
    @PreUpdate
    protected void onUpdate() {
    	updateTimestamp = new Timestamp(System.currentTimeMillis());
    }
    
    
    @OneToMany(mappedBy="cab")
    private List<Travel> travel;
    
}
