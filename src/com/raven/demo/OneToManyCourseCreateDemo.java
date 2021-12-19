package com.raven.demo;

import com.raven.entity.Course;
import com.raven.entity.Instructor;
import com.raven.entity.InstructorDetails;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyCourseCreateDemo {
	
	public static void main(String[] args) throws Exception {
		System.out.println(">>>>>>> Welcome One-To-Many Instructor Create Demo!!! <<<<<<<<");
		SessionFactory sessionFactory = null;
		Session session = null;

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
					.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
			session = sessionFactory.getCurrentSession();

			Instructor instructor = new Instructor("Susan", "Public", "susan.marry@yahoo.com");
			InstructorDetails instructorDetails = new InstructorDetails("https://www.susan2public.com/youtube",
					"Video Games");

			// LINK INSTRUCTOR_DETAILS WITH INSTRUCTOR
			instructor.setInstructorDetails(instructorDetails);

			session.beginTransaction();

			// THIS WILL ALSO SAVE INSTRUCTOR_DETAILS BECAUSE OF CascadeType.All IN
			// INSTRUCTOR CLASS
			session.save(instructor);

			session.getTransaction().commit();
			System.out.println("Instructor and its details saved to DB!!!");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
