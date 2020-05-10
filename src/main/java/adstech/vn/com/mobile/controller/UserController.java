package adstech.vn.com.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
import adstech.vn.com.mobile.model.User;
import adstech.vn.com.mobile.service.FileStorageService;
import adstech.vn.com.mobile.service.IUserService;
import adstech.vn.com.mobile.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	IUserService userSrvice;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@PostMapping("/create-user")
	public ResponseContract<?> create(@RequestBody User user) {
		return userSrvice.create(user);
	}

	@PostMapping("/login")
	public ResponseContract<?> login(@RequestBody Map<String, Object> input) {
		String phone = (String) input.get("numberPhone");
		String pass = (String) input.get("password");
		return userSrvice.login(phone, pass);
	}

	@PostMapping("/change-password")
	@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_STUDENT')")
	public ResponseContract<?> changePassword(@RequestBody Map<String, String> input) {
		return userSrvice.changePassword(input);
	}
	
	@GetMapping("/{userId}/get-user-by-id")
	@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_STUDENT')")
	public ResponseContract<?> getUerById(@PathVariable Integer userId){
		return userSrvice.getUserById(userId);
	}
	
	@PutMapping("/{userId}/update-user")
	@PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_STUDENT')")
	public ResponseContract<?> update(@PathVariable Integer userId,@RequestBody User user){
		
		user.setId(userId);
		return userSrvice.updateUser(user);
	}
	
	@GetMapping("/{postId}/get-post-by-id")
	public ResponseContract<?> getPostById(@PathVariable Integer postId){
		return userSrvice.getPostById(postId);
	}
}
