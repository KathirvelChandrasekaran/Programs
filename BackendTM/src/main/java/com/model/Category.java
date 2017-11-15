package com.model;
import java.io.Serializable;

import javax.persistence.Entity;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name="Category Product")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private int catID;
	private String catName;
	public int getCatID() {
		return catID;
	}
	public void setCatID(int catID) {
		this.catID = catID;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
}
