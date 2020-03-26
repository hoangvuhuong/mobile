package adstech.vn.com.mobile.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import adstech.vn.com.mobile.model.User;




public class UserPrincipal implements UserDetails{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String email;
	private String school;
	private Integer userClassId;
	private Date birthday; 
	private String numberPhone;
	private String avatar;
	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attributes;
	public UserPrincipal() {}
	public UserPrincipal(Integer id, String username, String email,String numberPhone,String avatar ,String school, Integer userClassId,
			Date birthday,Collection<? extends GrantedAuthority> authorities) {

		this.id = id;
		this.username = username;
		this.email = email;
		this.numberPhone = numberPhone;
		this.authorities = authorities;
		this.school = school;
		this.birthday = birthday;
		this.avatar = avatar;
		this.userClassId = userClassId;
	}

	public static UserPrincipal create(User user) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(user.getRole()));

		return new UserPrincipal(user.getId(), user.getUsername(), user.getEmail(),user.getNumberPhone(),user.getAvatar(),
				user.getSchool(), user.getUserClassId(),user.getBirthday(), authorities
		);
	}
	public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

	public Integer getId() {
		return id;
	}
	public Integer getUserClassId() {
		return userClassId;
	}
	public String getAvatar() {
		return avatar;
	}
	public String getSchool() {
		return school;
	}
	public Date getBirthday() {
		return birthday;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		
		return username;
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

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getEmail() {
		return email;
	}
	
	public String getNumberPhone() {
		return numberPhone;
	}
}