package adstech.vn.com.mobile.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import adstech.vn.com.mobile.model.User;

@Repository
public class UserRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public int create(User user) {
		String sql = "INSERT INTO tbl_users(username, password, role, number_phone, school, user_class_id, email, birthday, created_date)"
				+ " VALUES(:username, :password, :role, :numberPhone, :school, :userClassId, :email, :birthday, :createdDate);";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(user);
		return jdbcTemplate.update(sql, source);
	}
	
	public int update(User user) {
		String sql = "UPDATE tbl_users SET username =:username, password =:password, role =:role, number_phone =:numberPhone,"
				+ " school =:school, user_class_id =:userClassId, email =:email, birthday =:birthday, updated_date =:updatedDate "
				+ "WHERE id =:id;";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(user);
		return jdbcTemplate.update(sql, source);
	}
	
	public Optional<User> getByEmail(String email) {
		String sql="SELECT * FROM tbl_users where email =:email;";
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("email", email);
		try {
			return Optional
					.ofNullable(jdbcTemplate.queryForObject(sql, argMap, new BeanPropertyRowMapper<User>(User.class)));
		} catch (Exception e) {
			return Optional.ofNullable(null);
		
	}
	}
	
	public Optional<User> getByNumberPhone(String phone) {
		String sql="SELECT * FROM tbl_users where number_phone =:phone;";
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("phone", phone);
		try {
			return Optional
					.ofNullable(jdbcTemplate.queryForObject(sql, argMap, new BeanPropertyRowMapper<User>(User.class)));
		} catch (Exception e) {
			return Optional.ofNullable(null);
		
	}
	}
	public Optional<User> findByUserId(int id) {
		String sql="SELECT * FROM tbl_users where id =:id;";
		Map<String, Object> argMap = new HashMap<>();
		argMap.put("id", id);
		try {
			return Optional
					.ofNullable(jdbcTemplate.queryForObject(sql, argMap, new BeanPropertyRowMapper<User>(User.class)));
		} catch (Exception e) {
			return Optional.ofNullable(null);
		
		}
		}
}