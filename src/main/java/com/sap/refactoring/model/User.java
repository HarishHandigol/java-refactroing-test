package com.sap.refactoring.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User
{
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	String name;

	@Column(name = "email", nullable = false)
	String email;

	@Column(name = "roles")
	List<String> roles;
}
