package com.tutorial.authserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tutorial.authserver.entity.User;

@SuppressWarnings("serial")
public class AuthUserDetail extends User implements UserDetails {

	public AuthUserDetail(User user) {
		super(user);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> grantedAuthorities = new ArrayList();
		roles.forEach(role -> {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.name));
			role.permissions.forEach(permission -> {
				grantedAuthorities.add(new SimpleGrantedAuthority(permission.name));
			});
		});
		return grantedAuthorities;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return super.password;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return super.username;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return super.accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return super.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return super.credentialsNonExpired;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return super.enabled;
	}
}
