package ID1.services;


import ID1.model.oneToOne.uni.Email;
import ID1.model.oneToOne.uni.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneTOOneUnidirectionalService {
    private SessionFactory sessionFactory;

    @Autowired
    public OneTOOneUnidirectionalService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    public void saveMessageAndEmail() {
        Long emailId;
        Long messageId;

        Session session = getSession();
        Transaction tx = session.beginTransaction();

        Email email = new Email("Broken");
        Message message = new Message("Broken");

//        email.setMessage(message);
        message.setEmail(email);

        session.save(email);
        session.save(message);

        emailId = email.getId();
        messageId = message.getId();
        System.out.println("emailId=============" + emailId);
        System.out.println("messageId=============" + messageId);

        System.out.println("############################################################");
        System.out.println("############################################################");
        System.out.println("email.getMessage()====" + email.getMessage());//null
        System.out.println("message.getEmail()====" + message.getEmail());
        tx.commit();
        session.close();


        session = getSession();
        tx = session.beginTransaction();
        email = (Email) session.get(Email.class, emailId);
        System.out.println("email" + email);
        message = (Message) session.get(Message.class, messageId);
        System.out.println("message" + message);
        System.out.println("=================================================================="+email.getMessage());
        System.out.println("=================================================================="+message.getEmail());
        tx.commit();
        session.close();
    }

}
