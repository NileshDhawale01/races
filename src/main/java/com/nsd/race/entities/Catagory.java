package com.nsd.race.entities;

import java.util.List;import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "catagory")
public class Catagory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "catagory_name" , nullable = false , length = 20)
	private String name;
	
	@Column(name = "catagory_desc" , nullable = false , length = 200)
	private String description;
	
	@OneToMany(mappedBy = "catagory" , fetch = FetchType.LAZY)
	private List<Race> races;
}
