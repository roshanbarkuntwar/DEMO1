package com.lhs.taxcpcAdmin.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "LHS_TAXCPC_APP_DETAILS")
public class AppDetails  implements Serializable{

private static final long serialVersionUID = 1L;
	
	@Id 
	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "lhssys_app_details_seq"))
	@GeneratedValue(generator = "generator")
	@Column(name = "app_code")
	private Integer app_code;
	
	@Column (name = "server_id")
	private String server_id;
	
	@Column (name = "app_name")
	private String app_name;
	
	@Column (name = "local_app_url")
	private String local_app_url;
	
	@Column (name = "public_app_url")
	private String public_app_url;
	
	@Column (name = "app_remark")
	private String app_remark;
	
	@Column (name = "lastupdate")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastupdate;
	
	@Column (name = "flag")
	private String flag;
}
