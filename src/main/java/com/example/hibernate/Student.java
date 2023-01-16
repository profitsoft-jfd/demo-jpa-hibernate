package com.example.hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = { "group", "phoneNumbers" })
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	private LocalDate birthDate;

	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_student")
	private List<PhoneNumber> phoneNumbers = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Group group;

	public Student(String name) {
		this.name = name;
	}
}
