package org.example;


import org.example.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("let's get start............!");

        SessionFactory sf = new Configuration().addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        Session session = null;
        Transaction transaction = null;
        boolean isSucess = false;



        try{
            session = sf.openSession();
//            ****  All the names(table,properties) in query used from Entity class not from db table. ****

//            Query<Employee> query = session.createQuery("FROM Employee",Employee.class);

//            Query<Employee> query = session.createQuery("FROM Employee where age > 22",Employee.class);

//            Query<Employee> query = session.createQuery("FROM Employee where city =: inputCity",Employee.class);
//            query.setParameter("inputCity","Beed");

            Query<Employee> query = session.createQuery("FROM Employee WHERE age IN ( :age1, :age2 )");
            query.setParameter("age1",22);
            query.setParameter("age2",23);

            List<Employee> employeeList = query.list();
            employeeList.forEach( employee -> System.out.println(employee) );

//            ------------------------------------------------------------------------
//            *** selecting single record ***

//            Query<String> query = session.createQuery("SELECT name FROM Employee");

//            List<String> nameList = query.list();
//            nameList.forEach( name -> System.out.println(name) );


            isSucess = true;
        }
        catch (HibernateException e)
        {
            if( transaction != null )
                transaction.rollback();
            e.printStackTrace();
        }
        catch (Exception e)
        {
            if( transaction != null )
                transaction.rollback();
            e.printStackTrace();
        }
        finally {
            if( transaction != null && isSucess )
                transaction.commit();
            if( session != null && session.isOpen() )
                session.close();
            if ( sf != null )
                sf.close();
        }


    }
}