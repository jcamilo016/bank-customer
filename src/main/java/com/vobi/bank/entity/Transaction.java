package com.vobi.bank.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Zathura Code Generator Version 9.0 http://zathuracode.org/
 *         www.zathuracode.org
 *
 */
@Entity
@Table(name = "transaction", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "tran_id", unique = true, nullable = false)
	private Integer tranId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "acco_id")
	@ToString.Exclude
	private Account account;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trty_id")
	@ToString.Exclude
	private TransactionType transactionType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_email")
	@ToString.Exclude
	private User users;

	@Column(name = "amount", nullable = false)
	private Double amount;

	@Column(name = "date", nullable = false)
	private Date date;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Transaction that = (Transaction) o;
		return tranId != null && Objects.equals(tranId, that.tranId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
