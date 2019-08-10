package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeletePacmanCourseDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();


        try {

            //start a transaction
            session.beginTransaction();

            int tempId = 10;
            Course tempCourse = session.get(Course.class,tempId);

            session.delete(tempCourse);


            //commit transaction
            session.getTransaction().commit();

        } finally {

            //add clean up code
            session.close();

            factory.close();
        }
    }
}