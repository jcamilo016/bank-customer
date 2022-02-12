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
@Table(name = "account", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "acco_id", unique = true, nullable = false)
	private String accoId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	@ToString.Exclude
	private Customer customer;

	@Column(name = "balance", nullable = false)
	private Double balance;

	@Column(name = "enable", nullable = false)
	private String enable;

	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "version", nullable = true)
	private Long version;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	@ToString.Exclude
	private List<RegisteredAccount> registeredAccounts = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	@ToString.Exclude
	private List<Transaction> transactions = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Account account = (Account) o;
		return accoId != null && Objects.equals(accoId, account.accoId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}