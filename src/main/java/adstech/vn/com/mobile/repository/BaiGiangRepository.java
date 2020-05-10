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

import adstech.vn.com.mobile.model.BaiGiang;

@Repository
public class BaiGiangRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public int create(BaiGiang baiGiang) {
		String sql = "INSERT INTO tbl_bai_giang(class_id, name, description, file, link_youtube,created_at) VALUES(:classId, :name, :description, :file,"
				+ ":linkYoutube, CURRENT_TIMESTAMP)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(baiGiang);
		jdbcTemplate.update(sql, source, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int update(BaiGiang baiGiang) {
		String sql = "UPDATE tbl_bai_giang SET class_id=:classId, name =:name,"
				+ " description =:description, file=:file, link_youtube=:linkYoutube, updatedAt = CURRENT_TIMESTAMP WHERE id =:id; ";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(baiGiang);
		return jdbcTemplate.update(sql, source);
	}
	
	public int delete(Integer id) {
		String sql = "DELETE FROM tbl_bai_giang WHERE id =:id;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", id);
		return jdbcTemplate.update(sql, maps);
	}
	
	public List<BaiGiang>getByClassId(Integer classId) {
		String sql = "SELECT * FROM tbl_bai_giang WHERE class_id =:classId";
		Map<String, Object> maps = new HashMap<>();
		maps.put("classId", classId);
		return jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<BaiGiang>(BaiGiang.class));
	}
}
