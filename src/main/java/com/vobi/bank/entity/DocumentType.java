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
@Table(name = "document_type", schema = "public")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DocumentType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "doty_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer dotyId;


	@Column(name = "enable", nullable = false)
	private String enable;

	@Column(name = "name", nullable = false)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "documentType")
	@ToString.Exclude
	private List<Customer> customers = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		DocumentType that = (DocumentType) o;
		return dotyId != null && Objects.equals(dotyId, that.dotyId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}