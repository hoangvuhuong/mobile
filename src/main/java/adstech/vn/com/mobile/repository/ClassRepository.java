package adstech.vn.com.mobile.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import adstech.vn.com.mobile.model.Class;

@Repository
public class ClassRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public int create(Class lop) {
		String sql = "INSERT INTO tbl_class(name, capcha, code, teacher_id, user_class_id, created_at)"
				+ " VALUES(:name, :capcha, :code, :teacherId, :userClassId, CURRENT_TIMESTAMP)";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(lop);
		return jdbcTemplate.update(sql, source);
	}

	public int update(Class lop) {
		String sql = "UPDATE tbl_class SET name =:name, capcha =:capcha, updated_at = CURRENT_TIMESTAMP WHERE id =:id";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(lop);
		return jdbcTemplate.update(sql, source);
	}

	public int delete(Integer classId) {
		String sql = "DELETE FROM tbl_class WHERE id =:id";
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("id", classId);
		return jdbcTemplate.update(sql, maps);
	}

	public List<Class> getByTeacherId(Integer teacherId) {
		String sql = "SELECT * FROM tbl_class WHERE teacher_id =:teacherId;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("teacherId", teacherId);
		return jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<Class>(Class.class));
	}

	public Class getById(Integer classId) {
		String sql = "SELECT * FROM tbl_class WHERE id =:id;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", classId);
		List<Class> list = jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<Class>(Class.class));
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
