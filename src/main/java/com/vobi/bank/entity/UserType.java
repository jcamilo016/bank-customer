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
@Table(name = "user_type", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usty_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ustyId;


	@Column(name = "enable", nullable = false)
	private String enable;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userType")
	@ToString.Exclude
	private List<Users> userses = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		UserType userType = (UserType) o;
		return ustyId != null && Objects.equals(ustyId, userType.ustyId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}