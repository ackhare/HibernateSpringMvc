package ID1.services;


import ID1.model.oneToMany.uni.AccountEntity;
import ID1.model.oneToMany.uni.EmployeeEntity;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OneToManyUnidirectionalService {
    private SessionFactory sessionFactory;

    @Autowired
    public OneToManyUnidirectionalService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void saveEmployeeAndAccounts() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();


        AccountEntity account1 = new AccountEntity("Account detail 1", 600);

        AccountEntity account2 = new AccountEntity("Account detail 2", 400);

        AccountEntity account3 = new AccountEntity("Account detail 3", 500);

        AccountEntity account4 = new AccountEntity("Account detail 4", 400);

        AccountEntity account5 = new AccountEntity("Account detail 5", 600);


        //Add new Employee object
        EmployeeEntity firstEmployee = new EmployeeEntity("demo-user-first@mail.com", "demo-one", "user-one", 23);

        EmployeeEntity secondEmployee = new EmployeeEntity("demo-user-second@mail.com", "demo-two", "user-two", 28);

        EmployeeEntity thirdEmployee = new EmployeeEntity("demo-user-third@mail.com", "demo-third", "user-third", 35);

        Set<AccountEntity> accountsOfFirstEmployee = new HashSet<AccountEntity>();
        accountsOfFirstEmployee.add(account1);
        accountsOfFirstEmployee.add(account2);

        Set<AccountEntity> accountsOfSecondEmployee = new HashSet<AccountEntity>();
        accountsOfSecondEmployee.add(account3);

        Set<AccountEntity> accountsOfThirdEmployee = new HashSet<AccountEntity>();
        accountsOfThirdEmployee.add(account4);
        accountsOfThirdEmployee.add(account5);

        firstEmployee.setAccounts(accountsOfFirstEmployee);
        secondEmployee.setAccounts(accountsOfSecondEmployee);
        thirdEmployee.setAccounts(accountsOfThirdEmployee);
        //Save Employee

        session.save(firstEmployee);
        session.save(secondEmployee);
        session.save(thirdEmployee);

        transaction.commit();
        session.close();

    }

    public void criteria() {

        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(EmployeeEntity.class);

            criteria.add(Restrictions.eq("email", "demo-user-second@mail.com"));
            criteria.add(Restrictions.gt("age", 25));
//            criteria.add(Restrictions.like("firstName", "demo%"));
            criteria.add(Restrictions.like("firstName", "demo", MatchMode.START));
//        OR
           /* SimpleExpression ageLessThan = Restrictions.lt("age", 25);
            SimpleExpression name = Restrictions.like("firstName", "demo-two", MatchMode.START);
            LogicalExpression logicalExpression = Restrictions.or(name, ageLessThan);
            criteria.add(logicalExpression);*/

//            Using Disjunction
          /*  Criterion ageLessThan = Restrictions.lt("age", 25);
            Criterion name = Restrictions.like("firstName", "demo-two", MatchMode.START);
            Disjunction disjunction = Restrictions.disjunction();
            disjunction.add(ageLessThan);
            disjunction.add(name);
            criteria.add(disjunction);*/

//        Using And
/*
            criteria.add(Restrictions.lt("age", 25));
            criteria.add(Restrictions.like("firstName", "demo%"));*/

//        criteria.add(Restrictions.or(Restrictions.eq("email", "demo-user-second@mail.com"), Restrictions.like("firstName", "demo%"), Restrictions.gt("age", 25)));

/*
//        Using Conjunction
            Conjunction conjunction = Restrictions.conjunction();
            conjunction.add(Restrictions.lt("age", 25));
            conjunction.add(Restrictions.like("firstName", "demo%"));
            criteria.add(conjunction);*/

//        For Sorting Query Results
           /* criteria.add(Restrictions.gt("age", 25));
            criteria.add(Restrictions.like("firstName", "demo", MatchMode.START));
            criteria.addOrder(Order.desc("age"));*/


       /*     List<EmployeeEntity> list = criteria.list();
            System.out.println("****************************SIZE**************************************"+list.size());

            System.out.println("=======================================================" + list.size());
            for (EmployeeEntity employeeEntity : list) {
                System.out.println("====================EMAIL=====================" + employeeEntity.getEmail());
                System.out.println("====================FIRST NAME=====================" + employeeEntity.getFirstName());
                System.out.println("====================AGE=====================" + employeeEntity.getAge());
                System.out.println("====================LAST NAME=====================" + employeeEntity.getLastName());

                //LAZY LOADING

                Set<AccountEntity> accountEntities = employeeEntity.getAccounts();
                for (AccountEntity accountEntity : accountEntities) {

                    System.out.println("================ACCOUNT NUMBER===============" + accountEntity.getAccountNumber());
                }
                System.out.println("_______________________________________________________________________________________");
            }*/

//        to find the Employee whose account contains more than 500 in desc order of account using manyToOne
        Criteria accountCriteria=session.createCriteria(AccountEntity.class);
        accountCriteria.add(Restrictions.ge("deposit",500));
        accountCriteria.addOrder(Order.desc("deposit"));
        Criteria employeeCriteria=accountCriteria.createCriteria("employee");
        List<AccountEntity> list=accountCriteria.list();
        System.out.println("=============================================="+list.size());
        for(AccountEntity accountEntity:list){


            System.out.println("====================Account Number==================="+accountEntity.getAccountNumber());
            System.out.println("====================Account Deposit==================="+accountEntity.getDeposit());
            System.out.println("====================EMAIL=====================" + accountEntity.getEmployee().getEmail());
            System.out.println("====================FIRST NAME=====================" + accountEntity.getEmployee().getFirstName());
            System.out.println("====================AGE=====================" + accountEntity.getEmployee().getAge());
            System.out.println("====================LAST NAME=====================" + accountEntity.getEmployee().getLastName());

        }
            transaction.commit();
            session.close();

        } catch (QueryException ex) {
            ex.printStackTrace();
        }

    }

    public void projections() {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(AccountEntity.class);
            ProjectionList projList = Projections.projectionList();
            projList.add(Projections.max("deposit"));
            projList.add(Projections.min("deposit"));
            projList.add(Projections.avg("deposit"));
            projList.add(Projections.countDistinct("deposit"));
            criteria.setProjection(projList);
            List<Object[]> list = criteria.list();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Object[] row = (Object[]) iterator.next();
                System.out.println("==========================MAXIMUM========================" + row[0]);
                System.out.println("==========================MINIMUM========================" + row[1]);
                System.out.println("==========================AVERAGE========================" + row[2]);
                System.out.println("==========================COUNT DISTINCT========================" + row[3]);
            }

            transaction.commit();
            session.close();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }


    }

    public void pagintion() {
        try {
            Session session = getSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(AccountEntity.class);
//            criteria.setProjection(Projections.rowCount());
            criteria.setFirstResult(0);
            criteria.setMaxResults(2);
            List<AccountEntity> list = criteria.list();

            System.out.println("====================SIZE==========================" + list.size());
            for (AccountEntity accountEntity : list) {


                System.out.println("====================Account Number===================" + accountEntity.getAccountNumber());
                System.out.println("====================Account Deposit===================" + accountEntity.getDeposit());
                System.out.println("====================EMAIL=====================" + accountEntity.getEmployee().getEmail());
                System.out.println("====================FIRST NAME=====================" + accountEntity.getEmployee().getFirstName());
                System.out.println("====================AGE=====================" + accountEntity.getEmployee().getAge());
                System.out.println("====================LAST NAME=====================" + accountEntity.getEmployee().getLastName());


            }

        } catch (NonUniqueResultException exception) {
            exception.printStackTrace();
        }


    }
}
