package com.spring.assingment;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.spring.jdbc.dao.StudentDao;
import com.spring.jdbc.entites.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Project Started...");
		ApplicationContext context = new ClassPathXmlApplicationContext("com/spring/assingment/config.xml");

		StudentDao studentdao = context.getBean("studentDao", StudentDao.class);

		
//        INSERT VALUE
//        Student student = new Student();
//        student.setId(206); 
//        student.setName("aman");
//        student.setCity("Gujarat");
//        
//        int result = studentdao.insert(student);
//        
//        System.out.println("No of Student added are:"+result);

		
		//UPDATE VALUE
//		Student student = new Student();
//		student.setId(206);
//		student.setName("raj");
//		student.setCity("Ahmedabad");
//		int result = studentdao.change(student);
//		System.out.println("No of value updated are :" + result);

		
		//DELETE
//		int result = studentdao.delete(206);
//		System.out.println("No of value deleted are : " + result);
		
		
		
		//FETCH
		Student student = studentdao.getStudent(205);
		System.out.println(student.getId()+" : "+student.getName()+" : "+student.getCity());
	}
}
