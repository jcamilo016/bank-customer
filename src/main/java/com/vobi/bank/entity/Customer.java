package com.vobi.bank.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 * 
 */
@Entity
@Table(name = "customer", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cust_id", unique = true, nullable = false)
	@NotNull
	private Integer custId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doty_id")
	@NotNull
	@ToString.Exclude
	private DocumentType documentType;

	@Column(name = "address", nullable = false)
	@NotNull
	@Size(min = 3, max = 100)
	private String address;

	@NotNull
	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@NotNull
	@Size(min = 1,max = 1)
	@Column(name = "enable", nullable = false)
	private String enable;

	@NotNull
	@Size(min = 1,max = 100)
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Size(min = 1,max = 100)
	@Column(name = "phone", nullable = false)
	private String phone;
	
	@NotNull
	@Size(min = 1,max = 100)
	@Column(name = "token")
	private String token;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	@ToString.Exclude
	private List<RegisteredAccount> registeredAccounts = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Customer customer = (Customer) o;
		return custId != null && Objects.equals(custId, customer.custId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}