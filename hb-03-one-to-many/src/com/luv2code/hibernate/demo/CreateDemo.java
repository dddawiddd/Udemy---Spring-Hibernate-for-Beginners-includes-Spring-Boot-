package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            //create the objects:

            Instructor tempInstructor = new Instructor("Dawid", "Bla", "db@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("Kasztaniaki", "swimming");


/*
            Instructor tempInstructor = new Instructor("Krzysztof", "Glum", "Glum@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("Makapaka", "running");
*/


            //associate the objects
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            //start a transaction
            session.beginTransaction();

            //save the instructor
            //this will also save tempInstructorDetail bcz @OneToOne(cascade = CascadeType.ALL) in Instructor class
            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}