package adstech.vn.com.mobile.service;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.User;

public interface IUserService {
	public ResponseContract<?> login(String userName, String pass);

	public ResponseContract<?> create(User user);

	
}