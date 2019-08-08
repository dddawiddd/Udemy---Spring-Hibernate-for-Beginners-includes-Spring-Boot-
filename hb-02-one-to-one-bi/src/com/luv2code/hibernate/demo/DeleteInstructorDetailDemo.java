package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();


            //get the instructor detail
            int tempId = 5;
            InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, tempId);

            //print the instructor detail
            System.out.println("InstructorDetail: " + tempInstructorDetail);

            //print the associated instructor
            System.out.println("Instructor: " + tempInstructorDetail.getInstructor());

            //delete the instructorDetail

            session.delete(tempInstructorDetail);

            //break bi-directional link
            tempInstructorDetail.getInstructor().setInstructorDetail(null);

            //remove the associated object reference
            session.getTransaction().commit();

        } catch (Exception exc) {
            exc.printStackTrace();

        } finally {
            session.close();
            factory.close();
        }
    }
}