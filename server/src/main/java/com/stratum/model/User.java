package com.stratum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="system_user")
public class User {
	
	@Id	
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="role_id")
	private long roleId;
	
	@Column(name="user_data_id")
	private long userDataId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getUserDataId() {
		return userDataId;
	}

	public void setUserDataId(long userDataId) {
		this.userDataId = userDataId;
	}
	
	@Override
	public String toString() {
		return "USER [id = "+id+"] [roleId = "+roleId+"] [userDataId = "+userDataId+"]";
	}
}
