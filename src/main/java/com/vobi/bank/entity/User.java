package com.vobi.bank.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */
@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_email", unique = true, nullable = false)
	private String userEmail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usty_id")
	@ToString.Exclude
	private UserType userType;

	@Column(name = "enable", nullable = false)
	private String enable;

	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "token")
	private String token;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	@ToString.Exclude
	private List<Transaction> transactions = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		User users = (User) o;
		return userEmail != null && Objects.equals(userEmail, users.userEmail);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}