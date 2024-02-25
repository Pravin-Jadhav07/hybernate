package com.jadhav;

import com.jadhav.model.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class I_U_D_Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure()
                .addAnnotatedClass(Student.class)  // no need to mapping class in hibernate.cfg.xml file
                .buildSessionFactory();

        Session session = null;
        Transaction transaction = null;
        boolean isSuccess = false;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Student student = new Student();
            student.setId(5); // specify only when update or delete operation  bcos @GeneratedValue(strategy = GenerationType.IDENTITY)
//            student.setName("rohit sharma");
//            student.setEmail("r@s.com");
//            student.setAge(42);
//            student.setCity("mumbai ka king");

            // USED FOR INSERT
//            session.save(student); //Duplicate
//            session.persist(student);
//            session.saveOrUpdate(student);

            // USED FOR UPDATE
//            session.update(student); // Duplicate
//            session.merge(student);

            // USED FOR DELETE -- if specify only id its working.
//            session.delete(student); // Duplicate
            session.remove(student);

            isSuccess = true;
        }
        catch ( HibernateException e){
            e.printStackTrace();
        }catch ( Exception e){
            e.printStackTrace();
        }
        finally {
            if ( isSuccess )
                transaction.commit();
            else
                transaction.rollback();

            session.close();
        }

        System.out.println("your job is done...");
    }
}
