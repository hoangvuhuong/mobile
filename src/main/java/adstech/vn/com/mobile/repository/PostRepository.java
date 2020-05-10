package adstech.vn.com.mobile.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import adstech.vn.com.mobile.model.Post;


@Repository

public class PostRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public int create(Post post) {
		String sql = "INSERT INTO tbl_posts(title, user_id, image,classId, created_at) VALUES(:title, :userId, :image,:classId, CURRENT_TIMESTAMP );";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(post);
		return jdbcTemplate.update(sql, source);
	}
	
	public int update(Post post) {
		String sql="UPDATE tbl_posts SET title =:title , user_id =:userId, image= :image,classId =:classId, updated_at = CURRENT_TIMESTAMP;";
		BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(post);
		return jdbcTemplate.update(sql, source);
	}
	
	public List<Post> getAll(){
		String sql = "SELECT * FROM tbl_posts ;";
		return jdbcTemplate.query(sql, new HashMap<>(), new BeanPropertyRowMapper<Post>(Post.class));
	}
	
	public int delete(Integer postId) {
		String sql = "DELETE FROM tbl_posts WHERE id =:id;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", postId);
		return jdbcTemplate.update(sql, maps);
	}
	
	public Post findById(Integer id) {
		String sql = "SELECT * FROM tbl_posts WHERE id =:id;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("id", id);
		List<Post> posts = jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<Post>(Post.class));
		if(posts != null && !posts.isEmpty()) {
			return posts.get(0);
		}
		return null;
	}
	
	public List<Post> getByUserId(Integer userId){
		String sql = "SELECT * FROM tbl_posts WHERE user_id =:userId;";
		Map<String, Object> maps = new HashMap<>();
		maps.put("userId", userId);
		
		return jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<Post>(Post.class));
	}
	
	public List<Post> getByClassId(Integer classId){
		String sql = "SELECT * FROM tbl_posts WHERE classId =:classId";
		Map<String, Object> maps = new HashMap<>();
		maps.put("classId", classId);
		return jdbcTemplate.query(sql, maps, new BeanPropertyRowMapper<Post>(Post.class));
	}
}
