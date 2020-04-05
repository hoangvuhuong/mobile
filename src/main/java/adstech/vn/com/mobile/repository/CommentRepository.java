package adstech.vn.com.mobile.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import adstech.vn.com.mobile.model.Comment;

@Repository
public class CommentRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	public int create(Comment comment) {
		String sql = "INSERT INTO tbl_comments(body, image, user_id, post_id, created_at) VALUES(:body, :image,"
				+ " :userId, :postId,CURRENT_TIMESTAMP);";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(comment);
		return jdbcTemplate.update(sql, source);
	}

	public int update(Comment comment) {
		String sql = "UPDATE tbl_comments SET body =:body, image =:image, user_id =:userId,"
				+ " post_id =:postId, updated_at = CURRENT_TIMESTAMP WHERE id =:id;";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(comment);
		return jdbcTemplate.update(sql, source);
	}
	
	public List<Comment> getByPostId(Integer postId) {
		String sql = "SELECT * FROM tbl_comments WHERE post_id = :postId;";
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("postId", postId);
		return jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<Comment>(Comment.class));
		
	}
	
	public int delete(Integer cmtId) {
		String sql = "DELETE FROM tbl_comments WHERE id =:id;";
		Map<String , Object> maps = new HashMap<>();
		maps.put("id", cmtId);
		return jdbcTemplate.update(sql, maps);
	}
}
