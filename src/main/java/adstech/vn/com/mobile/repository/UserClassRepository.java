package adstech.vn.com.mobile.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import adstech.vn.com.mobile.model.UserClass;

@Repository
public class UserClassRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public Integer create(UserClass userClass) {
		String sql = "INSERT INTO tbl_user_class(user_id, class_id) VALUES(:userId, :classId);";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(userClass);
		jdbcTemplate.update(sql, source, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int update(UserClass userClass) {
		String sql = "UPDATE tbl_user_class SET user_id =:userId, class_id=:classId WHERE id =:id";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(userClass);
		return jdbcTemplate.update(sql, source);
	}
	
	public  int delete(Integer id) {
		String sql = "DELETE FROM tbl_user_class WHERE id =:id;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", id);
		return jdbcTemplate.update(sql, maps);
	}
	
	public List<Integer> getClassIdByUserId(Integer userId) {
		String sql = "SELECT class_id FROM tbl_user_class WHERE user_id =:userId;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("userId", userId);
		return jdbcTemplate.queryForList(sql, maps, Integer.class);
	}
	
	
	public List<Integer> getUserIdByClassId(Integer classId) {
		String sql = "SELECT user_id FROM tbl_user_class WHERE class_id =:classId;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("classId", classId);
		return jdbcTemplate.queryForList(sql, maps, Integer.class);
	}
	
	
}
