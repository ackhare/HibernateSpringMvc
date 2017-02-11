package ID1.services;

import ID1.model.manyToMany.ReaderEntity;
import ID1.model.manyToMany.SubscriptionEntity;
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
public class ManyToManyService {
    private SessionFactory sessionFactory;
    @Autowired
    public ManyToManyService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void createReaderAndSubscribers(){
        Session session=getSession();
        Transaction transaction=session.beginTransaction();
        //Add subscription
        SubscriptionEntity subOne = new SubscriptionEntity();
        subOne.setSubscriptionName("Entertainment");

        SubscriptionEntity subTwo = new SubscriptionEntity();
        subTwo.setSubscriptionName("Horror");

        Set<SubscriptionEntity> subs = new HashSet<SubscriptionEntity>();
        subs.add(subOne);
        subs.add(subTwo);

        //Add readers
        ReaderEntity readerOne = new ReaderEntity();
        readerOne.setEmail("demo-user1@mail.com");
        readerOne.setFirstName("demo");
        readerOne.setLastName("user");

        ReaderEntity readerTwo = new ReaderEntity();
        readerTwo.setEmail("demo-user2@mail.com");
        readerTwo.setFirstName("demo");
        readerTwo.setLastName("user");

        Set<ReaderEntity> readers = new HashSet<ReaderEntity>();
        readers.add(readerOne);
        readers.add(readerTwo);

        readerOne.setSubscriptions(subs);
        readerTwo.setSubscriptions(subs);

        session.save(readerOne);
        session.save(readerTwo);
        transaction.commit();
        session.close();
    }

}
