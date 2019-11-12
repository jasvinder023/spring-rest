package com.emp.rest.entity;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_info")
public class UserInfo implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_name")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Transient
	private String isActive;
	
	@Column(name="enabled")
	private Boolean enabled;

	
	@Column(name = "creation_date")
	private Date creationDate;

	@ManyToMany(fetch = FetchType.EAGER )
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;

	public UserInfo() {
		//this.createRoles();

	}

	public Long getId() {
		return id;
	}

	
	//create roles method
	// Create roles
		private void createRoles() {
			List<SimpleGrantedAuthority>authorities= new ArrayList<SimpleGrantedAuthority>();
			List<Role>rolesList=(List<Role>) getRoles();
			for (Role role : roles) {			
				authorities.add(new SimpleGrantedAuthority(role.getName()));

			}
			
		}
	
	public void setId(Long id) {
		this.id = id;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public Collection<Role> getRoles() {
		return roles;
	}	


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    
	    for (Role role: roles) {
	        authorities.add(new SimpleGrantedAuthority(role.getName()));
//	        role.getPrivileges().stream()
//	         .map(p -> new SimpleGrantedAuthority(p.getName()))
//	         .forEach(authorities::add);
	    }
	     
	    return authorities;
	}

	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + "*********" + '\''
				+ ", firstname='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\''
				+ "Roles= "+this.getRoles()+ '}';
	}
}
