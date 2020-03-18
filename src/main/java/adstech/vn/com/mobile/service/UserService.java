package adstech.vn.com.mobile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.User;
import adstech.vn.com.mobile.repository.UserRepository;
import adstech.vn.com.mobile.security.TokenProvider;
import adstech.vn.com.mobile.security.UserPrincipal;
import adstech.vn.com.mobile.util.CommonConstant;

@Service
public class UserService implements IUserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private TokenProvider tokenProvider;

	@Override
	public ResponseContract<?> login(String phone, String pass) {
		Optional<User> userOptional = userRepository.getByNumberPhone(phone);
		User user = null;
		if (userOptional.isPresent()) {
			user = userOptional.get();
		} else {
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "Tài khoản hoặc mật khẩu không đúng", null);
		}

		if (checkPass(pass, user.getPassword())) {
			UserPrincipal userPrincipal = UserPrincipal.create(user);
			String jwtToken = tokenProvider.createToken(userPrincipal);
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Đăng nhập thành công", jwtToken);
		}
		return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "Tài khoản hoặc mật khẩu không đúng", null);
	}

	@Override
	public ResponseContract<?> create(User user) {
		try {
		String pass = encrytePassword(user.getPassword());
		user.setPassword(pass);
		return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Đăng kí thành công", userRepository.create(user));
		}catch (Exception e) {
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
}
