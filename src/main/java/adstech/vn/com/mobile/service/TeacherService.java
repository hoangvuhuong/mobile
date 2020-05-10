package adstech.vn.com.mobile.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.BaiGiang;
import adstech.vn.com.mobile.model.Class;
import adstech.vn.com.mobile.repository.BaiGiangRepository;
import adstech.vn.com.mobile.repository.ClassRepository;
import adstech.vn.com.mobile.util.CommonConstant;

@Service
public class TeacherService {
	@Autowired
	ClassRepository classRepository;
	
	@Autowired
	BaiGiangRepository baiGiangRepository;
	
	public ResponseContract<?> createClass(adstech.vn.com.mobile.model.Class lop) {
		try {
			String code = generateCodeClass();
			lop.setCode(code);
			// Set user_class_id
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, null,
					classRepository.create(lop));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	private String generateCodeClass() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 5;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString.toUpperCase();
	}

	public ResponseContract<?> updateClass(Class lop) {
		try {
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "",
					classRepository.update(lop));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> deleteClassById(Integer classId) {
		try {
			return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "",
					classRepository.delete(classId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> getByTeacherId(Integer teacherId) {
		try {
			return new ResponseContract<List<Class>>(CommonConstant.RESPONSE_STATUS_SUCCESS, null,
					classRepository.getByTeacherId(teacherId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> getById(Integer classId){
		try {
			return new ResponseContract<Class>(CommonConstant.RESPONSE_STATUS_SUCCESS, null, classRepository.getById(classId));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}
	
	public ResponseContract<?> createBaiGiang(BaiGiang baiGiang){
	try {
		return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, null, baiGiangRepository.create(baiGiang));
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
	}	
	}
}
