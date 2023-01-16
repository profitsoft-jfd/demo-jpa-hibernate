package com.example.hibernate;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateDemoApplication implements CommandLineRunner {

	@Autowired
	private StudentService studentService;
	
	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("-------- begin ---------");
		
		Address address = new Address("London", "Some street", "12a");
		String group = "GR1";
		Student john = studentService.createStudent("John", LocalDate.of(2000, 12, 20), address, group);
		Student mary = studentService.createStudent("Mary", LocalDate.of(2002, 5, 15), address, group);
		
		System.out.println(studentService.listAll());
		
		System.out.println(studentService.getGroupNameForStudent(john.getId()));
//		john.getPhoneNumbers().add(new PhoneNumber(PhoneType.HOME, "010-12345"));
//		studentService.update(john);
		
		System.out.println("-------- end ---------");
	}

}
