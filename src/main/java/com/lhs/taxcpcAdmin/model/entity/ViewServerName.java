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
	@Table(name = "view_server_name")
	public class ViewServerName{
		
		@Id
		@Column(name = "server_type_code", nullable = true)
		private String server_type_code;
		@Column(name = "server_type_name", nullable = true)
		private String server_type_name;

}
