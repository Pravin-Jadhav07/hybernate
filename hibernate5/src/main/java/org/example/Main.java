package org.example;


import org.example.model.Employee;
import org.example.model.Address;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Employee.class).addAnnotatedClass(Address.class)
                .buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        Boolean isSucess = false;
        try{
            session = sessionFactory.openSession();
//            transaction = session.beginTransaction();
//
//            Address address = new Address();
//            address.setCity("beed");
//            address.setZipCode("431144");
//
//            Employee employee = new Employee();
//            employee.setName("navnath");
//            employee.setAddress(address);  // ONE to ONE


//            session.persist(address);
//            session.persist(employee);

            Employee employee = session.get(Employee.class,1);
            System.out.println(employee);

            isSucess=true;
        }
        catch (HibernateException e){
            if( transaction != null )
                transaction.rollback();

            e.printStackTrace();
        }
        catch (Exception e){
            if( transaction != null )
                transaction.rollback();

            e.printStackTrace();
        }
        finally {
            if( transaction != null && isSucess )
                transaction.commit();

            if( session != null  )
                session.close();

            if( sessionFactory != null )
                sessionFactory.close();
        }

    }
}