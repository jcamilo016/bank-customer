package com.vobi.bank.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 *
 */
@Entity
@Table(name = "registered_account", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredAccount implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "reac_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reacId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acco_id")
	@ToString.Exclude
	private Account account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cust_id")
	@ToString.Exclude
	private Customer customer;
	
	@Column(name = "enable", nullable = false)
	private String enable;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		RegisteredAccount that = (RegisteredAccount) o;
		return reacId != null && Objects.equals(reacId, that.reacId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
