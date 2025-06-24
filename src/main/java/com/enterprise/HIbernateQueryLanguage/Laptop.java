package com.enterprise.HIbernateQueryLanguage;

import javax.persistence.*;

@Entity
public class Laptop {
	@Id
	private int l_id;
	private String name;
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	@Override
	public String toString() {
		return "Laptop [l_id=" + l_id + ", name=" + name + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
