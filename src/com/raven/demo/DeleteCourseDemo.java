package com.raven.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.raven.entity.Course;
import com.raven.entity.Instructor;
import com.raven.entity.InstructorDetails;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		System.out.println(">>>>>>> Welcome One-To-Many Delete Course(s) Demo!!! <<<<<<<<");
		SessionFactory sessionFactory = null;
		Session session = null;

		try {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
					.addAnnotatedClass(InstructorDetails.class).addAnnotatedClass(Course.class).buildSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();

			// get a course
			int id = 2;
			Course course = session.get(Course.class, id);
			System.out.println(">>> Deleting Course :: " + course);

			// delete course
			session.delete(course);

			session.getTransaction().commit();
			System.out.println(">>>> Done <<<");
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
