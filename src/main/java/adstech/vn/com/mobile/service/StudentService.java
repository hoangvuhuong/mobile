package adstech.vn.com.mobile.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.UserClass;
import adstech.vn.com.mobile.repository.ClassRepository;
import adstech.vn.com.mobile.repository.UserClassRepository;
import adstech.vn.com.mobile.util.CommonConstant;

@Service
public class StudentService {
	@Autowired
	ClassRepository classRepository;

	@Autowired
	UserClassRepository userClassRepository;

	public ResponseContract<?> joinClass(Map<String, Object> input) {
		try {
			adstech.vn.com.mobile.model.Class lop = classRepository.getById((Integer) input.get("classId"));
			Integer userId = (Integer) input.get("userId");
			String capcha = (String) input.get("capcha");
			String code = (String) input.get("code");
			if (capcha.equals(lop.getCapcha()) && code.equals(lop.getCode())) {
				UserClass userClass = new UserClass();
				userClass.setClassId((Integer) input.get("classId"));
				userClass.setUserId(userId);
				Integer userClassId = userClassRepository.create(userClass);
				// lop.setUserClassId(userClassId);
				return new ResponseContract<Integer>(CommonConstant.RESPONSE_STATUS_SUCCESS, "Tham gia lop thanh cong",
						userClassId);
			} else
				return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, "Sai ma code hoac capcha",
						null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}

	public ResponseContract<?> getClassByUserId(Integer userId) {
		try {
			List<Integer> listClassId = userClassRepository.getClassIdByUserId(userId);
			List<adstech.vn.com.mobile.model.Class> listClass = new ArrayList<adstech.vn.com.mobile.model.Class>();
			for (Integer id : listClassId) {
				listClass.add(classRepository.getById(id));
			}
			return new ResponseContract<List<adstech.vn.com.mobile.model.Class>>(CommonConstant.RESPONSE_STATUS_SUCCESS,
					null, listClass);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseContract<String>(CommonConstant.RESPONSE_STATUS_FAILURE, e.getMessage(), null);
		}
	}
}
