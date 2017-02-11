package ID1.services;

import ID1.model.student.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentService {
    private SessionFactory sessionFactory;

    @Autowired
    public StudentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void create() {
        int id = 0;
        //Save

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student("sunny", "jaswal", "sunny@gmail.com", 19);

        id = (Integer) session.save(student);
        System.out.println("==================ID============" + student.getId());

        session.save(new Student("aditya","Pandey","aditya@gmail.com",20));
        session.save(new Student("Supriyo","sen","Supriyo@gmail.com",20));
        session.save(new Student("Atif","Ahmed","Atif@gmail.com",24));


        transaction.commit();

//        session.save(student);//works outside boundary

        session.close();


    }

    public void persist() {
        Session session1 = getSession();
        Transaction transaction1 = session1.beginTransaction();
        Student student = new Student();
        student.setAge(20);
        student.setFirstName("persist");
        student.setLastName("demo");
        student.setEmailAddress("pesrist@gmail.com");
        session1.persist(student);
        transaction1.commit();
//        session1.persist(student);//gives exception

        session1.close();
    }

    public void saveOrUpdate() {
        //Save

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Student student = new Student("sunny", "jaswal", "sunny@gmail.com", 19);

        int id = (Integer) session.save(student);
        System.out.println("==================ID============" + id);
        transaction.commit();
        session.close();

        //SaveOrUpdate
        session = getSession();
        transaction = session.beginTransaction();
        student.setEmailAddress("aditya@gmail.com");
        student.setFirstName("pandey");
        session.saveOrUpdate(student);
        System.out.println("==================Email of the student============" + student.getEmailAddress());
        System.out.println("==================First Name of the student============" + student.getFirstName());
        transaction.commit();

        session.close();
    }

    public void merge() {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Student student = new Student("siddhart", "shahi", "sid@gmail.com", 19);

        int id = (Integer) session.save(student);
        System.out.println("==================ID============" + id);
        transaction.commit();
        session.close();

        //student is in detached state now
        student.setEmailAddress("merge@gmail.com");
        session = getSession();
        transaction = session.beginTransaction();
        Student student1 = (Student) session.merge(student);

        transaction.commit();
        System.out.println("=======================Email Id of the Student=====================" + student.getEmailAddress());
        session.close();
    }

    public void refresh() {
        Session session = getSession();

        Transaction transaction = session.beginTransaction();
        Student student = new Student("pravesh", "shukla", "pravesh@gmail.com", 19);

        int id = (Integer) session.save(student);
        System.out.println("==================ID============" + id);
        transaction.commit();
        session.close();

        //student is in detached state
        student.setEmailAddress("refresh@gmail.com");
        session = getSession();
        transaction = session.beginTransaction();
        session.refresh(student);
        transaction.commit();
        System.out.println("=======================Email Id of the Student=====================" + student.getEmailAddress());
//      email of the student will br pravesh@gmail.com
        session.close();
    }

    public Student getOrLoad() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, new Integer(20));
//        Student student = (Student) session.load(Student.class, new Integer(20));
//        Student student=(Student) session.load(Student.class,new Integer(20));

        System.out.println("######################################");
        System.out.println("===============STUDENT================" + student.getId());
        System.out.println("===============STUDENT================" + student.getEmailAddress());
        System.out.println("######################################");
        return student;
    }


    public Student getOrLoad(Integer id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.get(Student.class, id);
//        Student student = (Student) session.load(Student.class, id);
        transaction.commit();
        session.close();
        return student;
    }

    public int hqlUpdate() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "update Student set firstName=:firstName";
        Query query = session.createQuery(hql);
        query.setString("firstName", "Pankaj");
        int numberOfRowsAffected = query.executeUpdate();
        transaction.commit();
        session.close();
        return numberOfRowsAffected;


    }

    public int hqlDelete() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "delete from Student where firstName=:firstName";
        Query query = session.createQuery(hql);
        query.setString("firstName", "Pankaj");
        int numberOfRowsAffected = query.executeUpdate();
        transaction.commit();
        session.close();
        return numberOfRowsAffected;


    }

    public List<Student> hqlFrom() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from Student student";
        Query query = session.createQuery(hql);
        List<Student> studentList = (List<Student>) query.list();
        transaction.commit();
        session.close();
        return studentList;

    }

    public List<Object> hqlSelect() {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "select student.lastName,student.emailAddress from Student student";
        Query query = session.createQuery(hql);
        List<Object> list = query.list();
        transaction.commit();
        session.close();
        return list;

    }

    public Student firstLevelCache(Integer id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Student student = (Student) session.load(Student.class, id);
        System.out.println("************************************" + student.getFirstName());
        System.out.println("************************************" + student.getFirstName());

        //Retriving student object for second time with same id from same session

/*      student=(Student)session.load(Student.class,id);
        System.out.println("=====================================================");
        System.out.println("************************************"+student.getFirstName());
        System.out.println("=====================================================");*/

        transaction.commit();
        session.close();
        return student;

    }



}
