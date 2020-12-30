import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {
    private static SessionFactory factory;
    {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

    }

    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, String tel, int salary){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(fname, lname, tel, salary);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees */
    public List<Employee> listEmployees( ){
        List<Employee> employees = new LinkedList<Employee>();
        List<Employee> emp = new LinkedList<Employee>();
        Session session = factory.openSession();
        Transaction tx = null;
        String name, surname, telephone;
        int salary;

        try {
            tx = session.beginTransaction();
            employees = session.createQuery("FROM Employee").list();
            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
                Employee employee = (Employee) iterator.next();
                name  = employee.getFirstName();
                surname = employee.getLastName();
                telephone = employee.getTelephone();
                salary = employee.getSalary();
                emp.add(new Employee(name, surname, telephone, salary));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return emp;
    }

    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            employee.setSalary( salary );
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to  READ all the clients */
    public List<Client> listClients( ){
        List<Client> clients = new LinkedList<Client>();
        List<Client> cli = new LinkedList<Client>();
        Session session = factory.openSession();
        Transaction tx = null;
        String name, surname, telephone, email;

        try {
            tx = session.beginTransaction();
            clients = session.createQuery("FROM Client").list();
            for (Iterator iterator = clients.iterator(); iterator.hasNext();){
                Client client = (Client) iterator.next();
                name  = client.getFirstName();
                surname = client.getLastName();
                telephone = client.getTelephone();
                email = client.getEmail();
                cli.add(new Client(name, surname, telephone, email));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return cli;
    }

    /* Method to CREATE a client in the database */
    public Integer addClient(String fname, String lname, String tel, String email){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer clientID = null;

        try {
            tx = session.beginTransaction();
            Client client = new Client(fname, lname, tel, email);
            clientID = (Integer) session.save(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return clientID;
    }

}