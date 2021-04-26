package com.voucher.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {
	 @Id
	 private String _id;
	// Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	 private ERole name;
	 /* 
	 private String role;

 public String getRole() {
	     return role;
	 }

	 public void setRole(String role) {
	     this.role = role;
	 }	 
*
*
*/
	 public Role() {
	}
	 public Role(ERole name) {
		    this.name = name;
		  }

	public String get_id() {
		 return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	 public ERole getName() {
		    return name;
		  }

    public void setName(ERole name) {
		    this.name = name;
	}

}
