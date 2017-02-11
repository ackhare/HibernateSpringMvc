package ID1.services;

import ID1.model.inheritance.singleTable.FourWheeler;
import ID1.model.inheritance.singleTable.TwoWheeler;
import ID1.model.inheritance.singleTable.Vehicle;
import ID1.model.inheritance.singleTable.inheritance.joined.Cheque;
import ID1.model.inheritance.singleTable.inheritance.joined.CreditCard;
import ID1.model.inheritance.singleTable.inheritance.joined.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InheritanceService {
    private SessionFactory sessionFactory;

    @Autowired
    public InheritanceService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void singleTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            Vehicle vehicle = new Vehicle();
            vehicle.setName("CAR");

            TwoWheeler twoWheeler = new TwoWheeler();
            twoWheeler.setName("PULSAR");
            twoWheeler.setSteeringHandle("PULSAR Steering Handle");

            FourWheeler fourWheeler = new FourWheeler();
            fourWheeler.setName("i10");
            fourWheeler.setSteeringWheel("i10 Steering wheel");

            session.save(vehicle);
            session.save(twoWheeler);
            session.save(fourWheeler);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }


    }

    public void joined() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSession();
            transaction = session.beginTransaction();
            session = getSession();
            transaction = session.beginTransaction();

            Payment payment=new Payment();
            payment.setAmount(500);

            Cheque cheque=new Cheque();
            cheque.setCreditCardType("Debit");
            cheque.setAmount(1000);

            CreditCard creditCard=new CreditCard();
            creditCard.setChequeType("Oversees");
            creditCard.setAmount(1000);

            session.save(payment);
            session.save(cheque);
            session.save(creditCard);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
