package com.nsd.race.entities;

import java.util.List;

import jakarta.persistence.Column;
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
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id" ,nullable = false)
	private Integer id;
	
	@Column(name = "company_name" , nullable = false , length = 50)
	private String name;
	
	@Column(name = "company_desc" , nullable = false , length = 500)
	private String description;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<Cars> cars;
}
