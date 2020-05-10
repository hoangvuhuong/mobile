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

import adstech.vn.com.mobile.model.BaiTap;

@Repository
public class BaiTapRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public int create(BaiTap baiTap) {
		String sql = "INSERT INTO tbl_bai_tap(name, class_id, link, start, end, create_at) VALUES(:name, :classId, :link, "
				+ ":start, :end, CURRENT_TIMESTAMP)";
				KeyHolder keyHolder = new GeneratedKeyHolder();
				BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(baiTap);
				jdbcTemplate.update(sql, source, keyHolder);
				return keyHolder.getKey().intValue();
	}
	
	public int update(BaiTap baiTap) {
		String sql = "UPDATE tbl_bai_tap SET name =:name,"
				+ " class_id =:classId, link =:link, start =:start, end =:end, update_at = CURRENT_TIMESTAMP WHERE id =:id";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(baiTap);
		return jdbcTemplate.update(sql, source);
	}
	
	public int delete(Integer id) {
		String sql = "DELETE FROM tbl_bai_tap WHERE id =:id";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", id);
		return jdbcTemplate.update(sql, maps);
	}
	
	public BaiTap getByStudentId(Integer id){
		String sql = "SELECT * FROM tbl_bai_tap WHERE id =:id";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", id);
		List<BaiTap> list = jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<BaiTap>(BaiTap.class));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
}
