package adstech.vn.com.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.Post;
import adstech.vn.com.mobile.model.User;
import adstech.vn.com.mobile.repository.PostRepository;
import adstech.vn.com.mobile.repository.UserClassRepository;
import adstech.vn.com.mobile.repository.UserRepository;
import adstech.vn.com.mobile.security.TokenProvider;
import adstech.vn.com.mobile.security.UserPrincipal;
import adstech.vn.com.mobile.util.AuthenUtil;
import adstech.vn.com.mobile.util.CommonConstant;

@Service
public class UserService implements IUserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;

	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	UserClassRepository userClassRepository;
	
	@Override
	public ResponseContract<?> login(String phone, String pass) {
		Optional<User> userOptional = userRepository.getByNumberPhone(phone);
		User user = null;
		if (userOptional.isPresent()) {
			user = userOptional.get();
		} else {
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE,
					"Tài khoản hoặc mật khẩu không đúng", null);
		}

		if (checkPass(pass, user.getPassword())) {
			UserPrincipal userPrincipal = UserPrincipal.create(user);
			String jwtToken = tokenProvider.createToken(userPrincipal);
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Đăng nhập thành công",
					jwtToken);
		}
		return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE,
				"Tài khoản hoặc mật khẩu không đúng", null);
	}

	@Override
	public ResponseContract<?> create(User user) {
		try {
			String pass = encrytePassword(user.getPassword());
			user.setPassword(pass);
			Optional<User> user1 = userRepository.getByEmail(user.getEmail());
			if (user1.isPresent())
				return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "Email đã tồn tại", null);
			Optional<User> user2 = userRepository.getByNumberPhone(user.getNumberPhone());
			if (user2.isPresent())
				return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "Số điện thoại đã tồn tại",
						null);
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Đăng kí thành công",
					userRepository.create(user));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public static boolean checkPass(String pass, String dataBasePass) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(pass, dataBasePass);
	}

	public static String encrytePassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	@Override
	public ResponseContract<?> changePassword(Map<String, String> input) {
		try {
			if (!input.get("newPassword1").equals(input.get("newPassword2"))) {
				return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE,
						"Nhập lại mật khẩu không đúng", null);
			} else {

				Optional<User> userOptional = userRepository.getByEmail(AuthenUtil.getPrincipal().getEmail());
				User user = null;
				if (userOptional.isPresent()) {
					user = userOptional.get();
				}
				String oldPass = input.get("oldPassword");
				if (checkPass(oldPass, user.getPassword())) {
					String newPass = encrytePassword(input.get("newPassword1"));
					user.setPassword(newPass);
					return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS,
							"Đổi mật khẩu thành công", userRepository.update(user));
				} else {
					return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE,
							"Mật khẩu cũ không chính xác", null);
				}
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	@Override
	public ResponseContract<?> getUserById(int id) {
		try {
			User user = userRepository.getById(id);
			if (user != null) {
				if (AuthenUtil.getPrincipal().getEmail().equals(user.getEmail())) {
					return new ResponseContract<User>(CommonConstant.RESPONSE_STATUS_SUCCESS, null, user);
				}
				else return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "Authentication failed", null);
					
			}
				return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "user khong ton tai", null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	@Override
	public ResponseContract<?> updateUser(User user) {
		try {
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Cập nhật thành công",
					userRepository.update(user));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	@Override
	public ResponseContract<?> getUserByClassId(Integer classId) {
		try {
			List<Integer> listUserId = userClassRepository.getUserIdByClassId(classId);
			List<User> listUser = new ArrayList<User>();
			for(Integer id : listUserId) {
				listUser.add(userRepository.getById(id));
			}
			return new ResponseContract<List<User>>(CommonConstant.RESPONSE_STATUS_SUCCESS, null, 
					listUser);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	@Override
	public ResponseContract<?> getPostById(Integer postId) {
		try {
			return new ResponseContract<Post>(CommonConstant.RESPONSE_STATUS_SUCCESS, null, postRepository.findById(postId));
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	@Override
	public ResponseContract<?> getPostByClassId(Integer classId) {
		try {
			return new ResponseContract<List<Post>>(CommonConstant.RESPONSE_STATUS_SUCCESS, null, postRepository.getByClassId(classId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}
	
	
}
