package com.nsd.race.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "races")
public class Race {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "race_id" , nullable = false)
	private Integer id;
	
	@Column(name = "country" , nullable = false, length = 50)
	private String country;
	
	@Column(name = "date" , nullable = false , length =  20)
	private String date;
	
	@Column(name = "track" , nullable = false , length = 100)
	private String track;
	
	@Column(name = "description" , nullable = false , length = 500)
	private String description;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "car_id")
	private Cars cars;
	
//	@ManyToOne(cascade = CascadeType.DETACH)
//	@JoinColumn(name = "company_id")
//	private Company company;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "catagory_id")
	private Catagory catagory;

}
