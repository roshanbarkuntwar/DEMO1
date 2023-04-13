package com.lhs.taxcpcAdmin.model.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.Immutable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Immutable
@Table(name = "view_doc_type")
public class ViewDocType {
	
	@Id
	@Column(name = "doc_type_code", nullable = true)
	private String doc_type_code;
	@Column(name = "doc_type_name", nullable = true)
	private String doc_type_name;
	@Column(name = "folder_name", nullable = true)
	private String folder_name;
	@Column(name = "flag", nullable = true)
	private String flag;


}
