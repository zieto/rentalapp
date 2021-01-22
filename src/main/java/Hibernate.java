import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import javax.swing.*;

@SuppressWarnings("Duplicates")
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
            JOptionPane.showMessageDialog(null, "Pracownik z takim numerem telefonu już istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
            if (tx!=null) tx.rollback();
            //e.printStackTrace();
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

    /* Method to DELETE an employee from the records */
    public void deleteEmployee(String name, String surname, String telephone, int salary){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            String hql = "SELECT id FROM Employee WHERE firstName = :firstname AND lastName = :lastname AND telephone = :telephone AND salary = :salary";
            Query getEmpID = session.createQuery(hql).setParameter("firstname", name).setParameter("lastname", surname).setParameter("telephone", telephone).setParameter("salary", salary);
            int EmployeeID = ((Number)getEmpID.getSingleResult()).intValue();
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE a client from the records */
    public void deleteClient(String name, String surname, String telephone, String email){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            String hql = "SELECT id FROM Client WHERE firstName = :firstname AND lastName = :lastname AND telephone = :telephone AND email = :email";
            Query getCliID = session.createQuery(hql).setParameter("firstname", name).setParameter("lastname", surname).setParameter("telephone", telephone).setParameter("email", email);
            int ClientID = ((Number)getCliID.getSingleResult()).intValue();
            tx = session.beginTransaction();
            Client client = session.get(Client.class, ClientID);
            session.delete(client);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE a car from the records */
    public void deleteCar(String brand, String model, String engine, String category, Boolean rented){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            String hql = "SELECT c.id FROM Car c, CarCategory cc WHERE c.brand = :brand AND c.model = :model AND c.engine = :engine AND c.rented = :rented AND c.cat_id = cc.id AND cc.name = :ccname";
            Query getCarID = session.createQuery(hql).setParameter("brand", brand).setParameter("model", model).setParameter("engine", engine).setParameter("ccname", category).setParameter("rented",rented);
            int CarID = ((Number)getCarID.getSingleResult()).intValue();
            tx = session.beginTransaction();
            Car car = session.get(Car.class, CarID);
            session.delete(car);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE rent from the records */
    public void deleteRent(String model, String kName, String kSurname, String pName, String pSurname, Date rent_date, Date ret_date){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            String hql = "SELECT r.id FROM Rent r, Car c, Client cli, Employee e WHERE r.car_id=c.id AND r.client_id=cli.id AND r.employee_id=e.id AND r.rent_date = :rentdt AND r.return_date = :retdt" +
                    " AND c.model = :model AND cli.firstName = :kname AND cli.lastName = :ksurname AND e.firstName = :pname AND e.lastName = :psurname";
            Query getRentID = session.createQuery(hql).setParameter("model", model).setParameter("kname", kName)
                    .setParameter("ksurname", kSurname).setParameter("pname", pName).setParameter("psurname", pSurname)
                    .setParameter("rentdt", rent_date).setParameter("retdt", ret_date);
            int RentID = ((Number)getRentID.getSingleResult()).intValue();
            tx = session.beginTransaction();
            Rent rent = session.get(Rent.class, RentID);
            session.delete(rent);
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

    /* Method to READ all rentals */
    public List<Rent> listRents( ){
        List<Rent> ren = new LinkedList<Rent>();
        Session session = factory.openSession();
        Transaction tx = null;
        String model, kName, kLastName, pName, pLastName;
        Date rent_date, ret_date;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT NEW Rent(s.model, k.firstName, k.lastName, p.firstName, p.lastName, w.rent_date, w.return_date) FROM Rent w, Employee p, Car s, Client k WHERE w.client_id=k.id AND w.car_id=s.id AND w.employee_id=p.id";
            final List<Rent> rents = session.createQuery(hql, Rent.class).list();
            for (Iterator iterator = rents.iterator(); iterator.hasNext();){
                Rent rent = (Rent) iterator.next();
                model = rent.getModel();
                kName = rent.getkName();
                kLastName = rent.getkLastName();
                pName = rent.getpName();
                pLastName = rent.getpLastName();
                rent_date = rent.getRent_date();
                ret_date = rent.getReturn_date();
                ren.add(new Rent(model, kName, kLastName, pName, pLastName, rent_date, ret_date));
             }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ren;
    }

    /* Method to READ all cars */
    public List<Car> listCars( ){
        List<Car> ren = new LinkedList<Car>();
        Session session = factory.openSession();
        Transaction tx = null;
        String cat, brand, model, engine;
        Boolean rented;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT NEW Car(k.name, s.brand, s.model, s.engine, s.rented) FROM Car s, CarCategory k WHERE k.id=s.cat_id";
            final List<Car> cars = session.createQuery(hql, Car.class).list();
            for (Iterator iterator = cars.iterator(); iterator.hasNext();){
                Car c = (Car) iterator.next();
                cat = c.getCat();
                brand = c.getBrand();
                model = c.getModel();
                engine = c.getEngine();
                rented = c.getRented();
                ren.add(new Car(cat, brand, model, engine, rented));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ren;
    }

    /* Method to READ available cars */
    public List<Car> listAvailableCars( ){
        List<Car> ren = new LinkedList<Car>();
        Session session = factory.openSession();
        Transaction tx = null;
        String cat, brand, model, engine;
        Boolean rented;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT NEW Car(k.name, s.brand, s.model, s.engine, s.rented) FROM Car s, CarCategory k WHERE k.id=s.cat_id AND s.rented=false";
            final List<Car> cars = session.createQuery(hql, Car.class).list();
            for (Iterator iterator = cars.iterator(); iterator.hasNext();){
                Car c = (Car) iterator.next();
                cat = c.getCat();
                brand = c.getBrand();
                model = c.getModel();
                engine = c.getEngine();
                rented = c.getRented();
                ren.add(new Car(cat, brand, model, engine, rented));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ren;
    }

    /* Method to READ all car categories */
    public List<CarCategory> listCarCategories( ){
        List<CarCategory> ren = new LinkedList<CarCategory>();
        Session session = factory.openSession();
        Transaction tx = null;
        String name, desc, pName, pLastName;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT NEW CarCategory(k.name, k.desc, p.firstName, p.lastName) FROM Employee p, CarCategory k WHERE k.admin_id=p.id";
            final List<CarCategory> cars = session.createQuery(hql, CarCategory.class).list();
            for (Iterator iterator = cars.iterator(); iterator.hasNext();){
                CarCategory c = (CarCategory) iterator.next();
                name = c.getName();
                desc = c.getDesc();
                pName = c.getpName();
                pLastName = c.getpLastName();
                ren.add(new CarCategory(name, desc, pName, pLastName));
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ren;
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
            JOptionPane.showMessageDialog(null, "Klient z takim adresem e-mail już istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
            if (tx!=null) tx.rollback();
            //e.printStackTrace();
        } finally {
            session.close();
        }
        return clientID;
    }

    /* Method to CREATE a car in the database */
    public Integer addCar(String brand, String model, String engine, String cat){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer carID = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT id FROM CarCategory WHERE name = :name";
            Query cc = session.createQuery(hql).setParameter("name", cat);
            int result = ((Number)cc.getSingleResult()).intValue();
            CarCategory carCategory = session.get(CarCategory.class, result);
            Car car = new Car(carCategory, brand, model, engine, false);
            carID = (Integer) session.save(car);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return carID;
    }

    /* Method to CREATE a car category in the database */
    public Integer addCarCategory(String name, String desc, String pName, String pLastName){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer carID = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT id FROM Employee WHERE firstName = :firstname AND lastName = :lastname";
            Query cc = session.createQuery(hql).setParameter("firstname", pName).setParameter("lastname", pLastName);
            int result = ((Number)cc.getSingleResult()).intValue();
            Employee employee = session.get(Employee.class, result);
            CarCategory carCategory = new CarCategory(employee, name, desc);
            carID = (Integer) session.save(carCategory);
            tx.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, "Taka kategoria już istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return carID;
    }

    /* Method to RENT a car */
    public Integer addRent(String carBrand, String carModel, String empName, String empLastName, String cliName, String cliLastName, int days){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer rentID = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT id FROM Employee WHERE firstName = :firstname AND lastName = :lastname";
            Query getEmpID = session.createQuery(hql).setParameter("firstname", empName).setParameter("lastname", empLastName);
            int employeeID = ((Number)getEmpID.getSingleResult()).intValue();
            Employee employee = session.get(Employee.class, employeeID);

            String hql2 = "SELECT id FROM Client WHERE firstName = :firstname AND lastName = :lastname";
            Query getCliID = session.createQuery(hql2).setParameter("firstname", cliName).setParameter("lastname", cliLastName);
            int clientID = ((Number)getCliID.getSingleResult()).intValue();
            Client client = session.get(Client.class, clientID);

            String hql3 = "SELECT id FROM Car WHERE model = :model AND brand = :brand";
            Query getCarID = session.createQuery(hql3).setParameter("brand", carBrand).setParameter("model", carModel);
            int carID = ((Number)getCarID.getSingleResult()).intValue();
            Car car = session.get(Car.class, carID);


            Date current_date = java.util.Calendar.getInstance().getTime();
            Calendar c = Calendar.getInstance();
            c.setTime(current_date);
            c.add(Calendar.DAY_OF_YEAR, days);
            Date end_date = c.getTime();

            Rent rent = new Rent(car, client, employee, current_date, end_date);
            rentID = (Integer) session.save(rent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rentID;
    }

    /* Method to RENT a car from client's view*/
    public Integer addClientRent(String carBrand, String carModel, int clientID, int days){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer rentID = null;
        try {
            tx = session.beginTransaction();

            String hql = "SELECT id FROM Employee ORDER BY RAND()";
            Query getEmpID = session.createQuery(hql);
            getEmpID.setMaxResults(1);
            int employeeID = ((Number)getEmpID.getSingleResult()).intValue();
            Employee employee = session.get(Employee.class, employeeID);

            String hql2 = "SELECT id FROM Car WHERE model = :model AND brand = :brand";
            Query getCarID = session.createQuery(hql2).setParameter("brand", carBrand).setParameter("model", carModel);
            int carID = ((Number)getCarID.getSingleResult()).intValue();
            Car car = session.get(Car.class, carID);

            Client client = session.get(Client.class, clientID);

            Date current_date = java.util.Calendar.getInstance().getTime();
            Calendar c = Calendar.getInstance();
            c.setTime(current_date);
            c.add(Calendar.DAY_OF_YEAR, days);
            Date end_date = c.getTime();

            Rent rent = new Rent(car, client, employee, current_date, end_date);
            rentID = (Integer) session.save(rent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return rentID;
    }

    /* Method to EDIT a car in the database */
    public void updateCar(String brand, String model, String engine, String cat, String newBrand, String newModel, String newEngine, String newCat){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT id FROM CarCategory WHERE name = :name";
            Query cc = session.createQuery(hql).setParameter("name", cat);
            int result = ((Number)cc.getSingleResult()).intValue();
            CarCategory res = session.get(CarCategory.class, result);

            String hql2 = "SELECT id FROM CarCategory WHERE name = :name";
            Query cc2 = session.createQuery(hql2).setParameter("name", newCat);
            int newResult = ((Number)cc2.getSingleResult()).intValue();

            CarCategory carCategory = session.get(CarCategory.class, newResult);

            String hql3 = "SELECT id From Car WHERE brand = :brand AND model = :model AND engine = :engine AND cat_id = :catid";
            Query cc3 = session.createQuery(hql3).setParameter("brand", brand).setParameter("model", model).setParameter("engine", engine).setParameter("catid", res);
            int carID = ((Number)cc3.getSingleResult()).intValue();
            Car car = new Car(carCategory, newBrand, newModel, newEngine, false);
            car.setId(carID);
            session.saveOrUpdate(car);

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to EDIT client's info in the database */
    public void updateClient(String oldName, String oldSurname, String oldEmail, String oldTelephone, String newName, String newSurname, String newEmail, String newTelephone){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String hql = "SELECT id From Client WHERE firstName = :fname AND lastName = :lname AND email = :email AND telephone = :tele";
            Query cc = session.createQuery(hql).setParameter("fname", oldName).setParameter("lname", oldSurname).setParameter("email", oldEmail).setParameter("tele", oldTelephone);
            int clientID = ((Number)cc.getSingleResult()).intValue();

            Client client = new Client(newName, newSurname, newTelephone, newEmail);
            client.setId(clientID);
            session.saveOrUpdate(client);

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } catch (PersistenceException ef){
            JOptionPane.showMessageDialog(null, "Klient z takim adresem e-mail już istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }finally {
            session.close();
        }
    }

    /* Method to EDIT employee's info in the database */
    public void updateEmployee(String oldName, String oldSurname, String oldTelephone, int oldSalary, String newName, String newSurname, String newTelephone, int newSalary){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String hql = "SELECT id From Employee WHERE firstName = :fname AND lastName = :lname AND salary = :salary AND telephone = :tele";
            Query cc = session.createQuery(hql).setParameter("fname", oldName).setParameter("lname", oldSurname).setParameter("salary", oldSalary).setParameter("tele", oldTelephone);
            int employeeID = ((Number)cc.getSingleResult()).intValue();

            Employee employee = new Employee(newName, newSurname, newTelephone, newSalary);
            employee.setId(employeeID);
            session.saveOrUpdate(employee);

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        catch (PersistenceException ef){
            JOptionPane.showMessageDialog(null, "Pracownik z takim numerem telefonu już istnieje!", "Błąd", JOptionPane.ERROR_MESSAGE);
        } finally {
            session.close();
        }
    }

    /* Method to EDIT rent info in the database */
    public void updateRent(String cname, String clastname, String eoldname, String eoldsurname, String enewname, String enewsurname, String model, Date oldRentDate, Date oldReturnDate, String newRentDate, String newReturnDate){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String ehql = "SELECT id From Employee WHERE firstName = :fname AND lastName = :lname";
            Query ecc = session.createQuery(ehql).setParameter("fname", enewname).setParameter("lname", enewsurname);
            int employeeID = ((Number)ecc.getSingleResult()).intValue();
            Employee employee = session.get(Employee.class, employeeID);

            String client_hql = "SELECT id From Client WHERE firstName = :fname AND lastName = :lname";
            Query clientcc = session.createQuery(client_hql).setParameter("fname", cname).setParameter("lname", clastname);
            int clientID = ((Number)clientcc.getSingleResult()).intValue();
            Client client = session.get(Client.class, clientID);

            String car_hql = "SELECT id From Car WHERE model = :model";
            Query carcc = session.createQuery(car_hql).setParameter("model", model);
            int carID = ((Number)carcc.getSingleResult()).intValue();
            Car car = session.get(Car.class, carID);

            String hql = "SELECT r.id FROM Rent r, Employee e, Client c, Car car WHERE r.employee_id=e.id AND r.client_id=c.id AND r.car_id=car.id " +
                    "AND e.firstName = :eoldname AND e.lastName = :eoldsurname AND car.model = :model AND c.firstName = :cname AND c.lastName = :clastname " +
                    "AND r.rent_date = :oldrentdt AND r.return_date = :oldreturndt";
            Query cc = session.createQuery(hql).setParameter("eoldname", eoldname).setParameter("eoldsurname", eoldsurname).setParameter("model", model)
                    .setParameter("cname", cname).setParameter("clastname", clastname).setParameter("oldrentdt", oldRentDate)
                    .setParameter("oldreturndt", oldReturnDate);
            int rentID = ((Number)cc.getSingleResult()).intValue();

            Date rentdt, returndt;

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            rentdt = formatter.parse(newRentDate);
            returndt = formatter.parse(newReturnDate);

            Rent rent = new Rent(car, client, employee, rentdt, returndt);
            rent.setId(rentID);
            session.saveOrUpdate(rent);

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

}