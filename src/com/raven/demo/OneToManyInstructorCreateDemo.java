package com.raven.demo;

import com.raven.entity.Course;
import com.raven.entity.Instructor;
import com.raven.entity.InstructorDetails;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyInstructorCreateDemo {

	public static void main(String[] args) throws Exception {
		System.out.println(">>>>>>> Welcome One-To-Many Instructor Create Demo!!! <<<<<<<<");
		SessionFactory sessionFactory = null;
		Session session = null;

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
					.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			// get the instructor
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);

			// create courses
			Course course1 = new Course("Air Guiter - The Ultimate Guide");
			Course course2 = new Course("Tha Pinball MasterClass");

			// add course to instructor
			instructor.add(course1);
			instructor.add(course2);

			// save course
			session.save(course1);
			session.save(course2);

			session.getTransaction().commit();
			System.out.println(">>> Courses saved to DB <<<");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
