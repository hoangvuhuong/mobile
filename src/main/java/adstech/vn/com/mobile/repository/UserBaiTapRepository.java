package adstech.vn.com.mobile.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import adstech.vn.com.mobile.model.UserBaiTap;

@Repository
public class UserBaiTapRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public int create(UserBaiTap userBaiTap) {
		String sql = "INSERT INTO  tbl_user_baitap(user_id,bai_tap_id) VALUES(:userId, :baiTapId)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(userBaiTap);
		jdbcTemplate.update(sql, source, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int update(UserBaiTap userBaiTap) {
		String sql = "UPDATE tbl_user_baitap SET user_id =:userId,bai_tap_id =:baiTapId WHERE id =:id";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(userBaiTap);
		return jdbcTemplate.update(sql, source);
	}
	
	public int delete(Integer id) {
		String sql = "DELETE FROM tbl_user_baitap WHERE id =:id";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", id);
		return jdbcTemplate.update(sql, maps);
	}
	
	public List<Integer> getListBaiTapIdByUserId(Integer userId){
		String sql = "SELECT bai_tap_id FROM tbl_user_baitap WHERE user_id =:userId";
		Map<String, Object> maps = new HashMap<>();
		maps.put("userId", userId);
		return jdbcTemplate.queryForList(sql, maps, Integer.class);
	}
}
