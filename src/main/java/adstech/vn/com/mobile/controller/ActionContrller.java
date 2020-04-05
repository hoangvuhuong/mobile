package adstech.vn.com.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.ActionService;
import adstech.vn.com.mobile.model.Comment;
import adstech.vn.com.mobile.model.Post;
import adstech.vn.com.mobile.service.FileStorageService;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_STUDENT')")
public class ActionContrller {
	@Autowired
	ActionService actionService;

	

	@PostMapping("/create-post")
	public ResponseContract<?> createPost( @RequestBody Post post) {
		
		return actionService.createPost(post);
	}
	
	@GetMapping("/get-all-post")
	public ResponseContract<?> getAllPost(){
		return actionService.getAllPost();
	}
	
	@DeleteMapping("/delete-post")
	public ResponseContract<?> deletePost(@RequestBody Map<String, Integer> input){
		
		return actionService.deletePostByUser(input.get("postId"), input.get("userId"));
	}
	
	@GetMapping("/{userId}/get-by-userId")
	public ResponseContract<?> getByUserID(@PathVariable Integer userId){
		return actionService.getPostByUserID(userId);
	}
	
	@PostMapping("/create-comment")
	public ResponseContract<?> createComment(@RequestBody Comment comment){
		return actionService.createComment(comment);
	}
	
	@GetMapping("/{postId}/get-comment-by-postId")
	public ResponseContract<?> getCommentByPostId(@PathVariable Integer postId){
		return actionService.getCommentByPostId(postId);
	}
	
	@PutMapping("/update-comment")
	public ResponseContract<?> updateComment(@RequestBody Comment comment){
		return actionService.updateComment(comment);
	}
	
	@DeleteMapping("/{commentId}/delete-comment")
	public ResponseContract<?> deleteComment(@PathVariable Integer commentId){
		return actionService.deleteComment(commentId);
	}
}
