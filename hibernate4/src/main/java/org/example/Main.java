package org.example;

import org.example.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
    In below program
    a. two sessions ( level-1 cache )
        - using first session we are fetching one object two times but due to level-1 cache query execute only one time
        - for second session same object but cache is level-1 so it will execute query again
        it means for every session there is seperate cache.
    b. level-2 cache
        - if we configure setup for level-2 (ehcache) cache
        - for that import some anotation javax version instead of jakarta
        - add 2 more dependancies in pom.xml file and two propeties in hibernate.cfg file and add two annotation in Entity class.
        - for every session there is only one cache and both cache execute query only one time.
*/

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = null;
        Session session = null;
        Session session1 = null;
        Transaction transaction = null;
        Boolean isSuccess=false;

        try {
            sessionFactory = new Configuration().configure().
                addAnnotatedClass(Student.class).buildSessionFactory();

            session = sessionFactory.openSession();

            Student student = session.get(Student.class,1);
            System.out.println(student);

            Student student1 = session.get(Student.class,1);
            System.out.println(student1);

            session1 = sessionFactory.openSession();

            Student student2 = session1.get(Student.class,1);
            System.out.println(student2);

            isSuccess = true;
        }
        catch (HibernateException e)
        {
            if (transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }
        catch (Exception e)
        {   if (transaction != null)
                transaction.rollback();

            e.printStackTrace();
        }
        finally {

            if(transaction != null &&  isSuccess )
                transaction.commit();

            if( session != null && session.isOpen() )
                session.close();

            if( sessionFactory != null )
                sessionFactory.close();
        }

    }
}