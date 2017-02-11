package ID1.services;


import ID1.model.oneToMany.bi.Account;
import ID1.model.oneToMany.bi.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class OneToManyBidirectionalService {
    private SessionFactory sessionFactory;

    @Autowired
    public OneToManyBidirectionalService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void saveEmployeeAndAccounts() {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Account account1 = new Account("123-345-65454");

        Account account2 = new Account("123-345-6542222");

        //Add new Employee object
        Employee emp = new Employee("demo-user@mail.com", "demo", "user");

        Set<Account> accounts = new HashSet<Account>();
        accounts.add(account1);
        accounts.add(account2);

        emp.setAccounts(accounts);
        //Save Employee
        session.save(emp);


        transaction.commit();
        session.close();

      /*  Session session=getSession();
       Transaction transaction=session.beginTransaction();
        Employee employee=(Employee)session.get(Employee.class,1);
        System.out.println(employee.getEmail());
        System.out.println(employee.getAccounts());
        transaction.commit();
        session.close();*/
    }
}
