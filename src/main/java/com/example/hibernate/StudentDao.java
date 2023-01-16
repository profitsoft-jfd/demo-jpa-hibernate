package com.example.hibernate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;

@Repository
public class StudentDao {

	@Autowired
	private EntityManager entityManager;

	public Student findStudentById(long id) {
		return entityManager.find(Student.class, id);
	}
	
	public Optional<Group> findGroupByName(String name) {
		return entityManager.createQuery("from Group where name=:name", Group.class)
			.setParameter("name", name)
			.setMaxResults(1)
			.getResultStream().findFirst();
	}

	public List<Student> listAll() {
		return entityManager.createQuery("from Student", Student.class).getResultList();
	}


	public void save(Student student) {
		entityManager.persist(student);
	}

	public void update(Student student) {
		entityManager.merge(student);
	}

	public void saveGroup(Group group) {
		entityManager.persist(group);
	}
}
