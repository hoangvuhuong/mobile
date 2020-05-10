package adstech.vn.com.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import adstech.vn.com.mobile.contract.ResponseContract;
import adstech.vn.com.mobile.service.StudentService;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_STUDENT')")
public class StudentController {
	@Autowired
	StudentService studentService;
	@PostMapping("/join-class")
	public ResponseContract<?> joinClass(@RequestBody Map<String, Object> input){
		return studentService.joinClass(input);
	}
	
	@GetMapping("/{userId}/get-class-student")
	public ResponseContract<?> getClassByStudentId(@PathVariable Integer userId){
		return studentService.getClassByUserId(userId);
	}
}
