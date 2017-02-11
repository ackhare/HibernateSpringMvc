package ID1.model.oneToOne.bi;


import javax.persistence.*;

@Entity

public class Email_Bi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String subject;
    @OneToOne //(mappedBy = "email_bi")
    Message_Bi messageBi;

    public Email_Bi() {
    }

    public Email_Bi(String subject) {
        setSubject(subject);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message_Bi getMessageBi() {
        return messageBi;
    }

    public void setMessageBi(Message_Bi messageBi) {
        this.messageBi = messageBi;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
