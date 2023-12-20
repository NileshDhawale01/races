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
@Table(name = "cars")
public class Cars {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id" , nullable = false)
	private Integer id;
	
	@Column(name = "car_name" , nullable = false , length = 50)
	private String name;
	
	@Column(name = "car_model" , nullable = false , length = 200)
	private String model;
	
	@Column(name = "price" , nullable = false , length = 100)
	private String price;
	
	@Column(name = "top_speed" , nullable = false , length = 50)
	private String topSpeed;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "company_id" , nullable = false)
	private Company company;
	
	
}
