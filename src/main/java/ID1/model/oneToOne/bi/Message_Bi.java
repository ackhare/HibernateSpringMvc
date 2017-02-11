package ID1.model.oneToOne.bi;

import javax.persistence.*;
@Entity
public class Message_Bi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String content;

    @OneToOne
    Email_Bi emailbi;

    public Message_Bi() {
    }

    public Message_Bi(String message_bi) {
    setContent(message_bi);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Email_Bi getEmailbi() {
        return emailbi;
    }

    public void setEmail_bi(Email_Bi emailbi) {
        this.emailbi = emailbi;
    }
}
