package adstech.vn.com.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.model.Class;
import adstech.vn.com.mobile.repository.UserRepository;
import adstech.vn.com.mobile.service.TeacherService;
import adstech.vn.com.mobile.service.UserService;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_TEACHER')")
public class TeacherController {
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	UserService userService;
	@PostMapping("/create-class")
	public ResponseContract<?> createClass(@RequestBody Class lop){
		return teacherService.createClass(lop);
	}
	
	@PutMapping("/{classId}/update-class")
	public ResponseContract<?> updateClass(@PathVariable Integer classId, @RequestBody Class lop){
		lop.setId(classId);
		return teacherService.updateClass(lop);
	}
	
	@DeleteMapping("/{classId}/delete-class")
	public ResponseContract<?> deleteClass(@PathVariable Integer classId){
		return teacherService.deleteClassById(classId);
	}
	
	@GetMapping("/{classId}/get-by-classId")
	public ResponseContract<?> getClassById(@PathVariable Integer classId){
		return teacherService.getById(classId);
	}
	
	@GetMapping("/{userId}/get-by-teacherId")
	public ResponseContract<?> getByTeacherId(@PathVariable Integer userId){
		return teacherService.getByTeacherId(userId);
	}
	
	@GetMapping("/{classId}/get-student-by-classId")
	public ResponseContract<?> getStudentByClassId(@PathVariable Integer classId){
		
		return userService.getUserByClassId(classId);
	}
}
