package adstech.vn.com.mobile;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.User;
import adstech.vn.com.mobile.service.IUserService;
import adstech.vn.com.mobile.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	IUserService userSrvice;
	@PostMapping("/create-user")
	public ResponseContract<?> create(@RequestBody User user){
		return userSrvice.create(user);
	}
	
	@PostMapping("/login")
	public ResponseContract<?> login(@RequestBody Map<String, Object> input){
		String phone = (String)input.get("numberPhone");
		String pass = (String)input.get("password");
		return userSrvice.login(phone, pass);
	}
}
