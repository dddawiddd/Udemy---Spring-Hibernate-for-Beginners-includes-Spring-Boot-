package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {

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

            // create a course
            Course tempCourse = new Course("Pacman - How to Score One Million Points");

            session.save(tempCourse);

            //create students
            Student tempStudent1 = new Student("Dawid","Fra","fra@gmail.com");
            Student tempStudent2 = new Student("Paul","Rouch","paul@gmail.com");

            tempCourse.addStudent(tempStudent1);
            tempCourse.addStudent(tempStudent2);

            session.save(tempStudent1);
            session.save(tempStudent2);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {

            //add clean up code
            session.close();

            factory.close();
        }
    }
}