package com.example.hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	private StudentDao dao;

	public Student findStudentById(long id) {
		return dao.findStudentById(id);
	}

	@Transactional
	public Student createStudent(String name, LocalDate birthDate, Address address, String groupName) {
		Student student = new Student(name);
		student.setBirthDate(birthDate);
		student.setAddress(address);
	
		if (groupName != null) {
			findGroupByName(groupName).ifPresentOrElse(student::setGroup, () -> {
				Group group = new Group(groupName);
				student.setGroup(group);
				group.getStudents().add(student);
				dao.saveGroup(group);
			});
		}
		dao.save(student);
		return student;
	}
	
	@Transactional
	public void update(Student student) {
		dao.update(student);
	}

	@Transactional
	public String getGroupNameForStudent(long studentId) {
		Student student = findStudentById(studentId);
		student.getGroup().getId();
		return student.getGroup().getName();
	}

	public Optional<Group> findGroupByName(String groupName) {
		return dao.findGroupByName(groupName);
	}
	
	public List<Student> listAll() {
		return dao.listAll();
	}
}
