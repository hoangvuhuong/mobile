package adstech.vn.com.mobile.service;

import java.util.Map;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.User;

public interface IUserService {
	public ResponseContract<?> login(String userName, String pass);

	public ResponseContract<?> create(User user);

	public ResponseContract<?> changePassword(Map<String, String> input);
	
	public ResponseContract<?> getUserById(int id);
	
	public ResponseContract<?> updateUser(User user);
	
	public ResponseContract<?> getUserByClassId(Integer classId);
	
	public ResponseContract<?> getPostById(Integer postId);
}