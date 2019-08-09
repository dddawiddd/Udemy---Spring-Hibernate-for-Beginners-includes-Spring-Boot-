package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class getCoursesLater {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            int tempId = 1;
            Instructor tempInstructor = session.get(Instructor.class, tempId);

            System.out.println("<<<< tempInstructor: " + tempInstructor);

            session.getTransaction().commit();
            session.close();


            session = factory.getCurrentSession();
            session.beginTransaction();

            Query<Course> query = session.createQuery("select c from Course c "
                    + "where c.instructor.id=:theInstructorId", Course.class);

            query.setParameter("theInstructorId", tempId);

            List<Course> tempCourses = query.getResultList();

            System.out.println("<<<< tempCourses: " + tempCourses);

            //assign courses to instructor
            tempInstructor.setCourses(tempCourses);

            System.out.println("<<<< tempCourses from Instructor: " + tempInstructor.getCourses());

            session.getTransaction().commit();

        } finally {
            session.close();
            factory.close();
        }
    }
}
