package ID1.services;


import ID1.model.oneToOne.bi.Email_Bi;
import ID1.model.oneToOne.bi.Message_Bi;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOneBirectionalService {
    private SessionFactory sessionFactory;

    @Autowired
    public OneToOneBirectionalService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }


    public void saveMessageAndEmailBirectional() {
        Long emailId;
        Long messageId;

        Session session = getSession();
        Transaction tx = session.beginTransaction();

        Email_Bi email_bi = new Email_Bi("RIGHT WAY");
        Message_Bi message_bi = new Message_Bi("RIGHT WAY");

        email_bi.setMessageBi(message_bi);
        message_bi.setEmail_bi(email_bi);

        session.save(email_bi);
        session.save(message_bi);

        emailId = email_bi.getId();
        messageId = message_bi.getId();

        tx.commit();
        session.close();
        System.out.println("############################################################");
        System.out.println("############################################################");

        System.out.println(email_bi.getMessageBi());
        System.out.println(message_bi.getEmailbi());
        System.out.println("############################################################");
        System.out.println("############################################################");

        session = getSession();
        tx = session.beginTransaction();
        email_bi = (Email_Bi) session.get(Email_Bi.class, emailId);
        System.out.println("==========================================================");
        System.out.println(email_bi);
        message_bi = (Message_Bi) session.get(Message_Bi.class, messageId);
        System.out.println(message_bi);
        System.out.println("===========================================================");

        tx.commit();
        session.close();

        System.out.println("***********************************************************");
        System.out.println("************************************************************");

        System.out.println(email_bi.getMessageBi());
        System.out.println(message_bi.getEmailbi());
        System.out.println(email_bi.getId());
        System.out.println(message_bi.getId());
        System.out.println("*************************************************************");
        System.out.println("*************************************************************");

    }
}
