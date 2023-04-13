package com.lhs.taxcpcAdmin.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import lombok.Data;

@Data
@Entity
@Table(name = "VIEW_RET_QUT")
public class View_return_qt  implements Serializable{

private static final long serialVersionUID = 1L;
	
	
@Id 
@Column (name = "acc_year")
private String acc_year;

@Column (name = "quarter")
private String quarter;


@Column (name = "from_date")
private String from_date;

@Column (name = "to_date")
private String to_date;


@Column (name = "return_type")
private String return_type;



	


}