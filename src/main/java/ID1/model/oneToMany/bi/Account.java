package ID1.model.oneToMany.bi;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Table;


@Entity(name = "JoinTableAccountEntity")
@Table(name = "Account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class Account implements Serializable
{

    private static final long serialVersionUID = -6790693372846798580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer accountId;

    @Column(name = "ACC_NUMBER", unique = true, nullable = false, length = 100)
    private String accountNumber;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Account(){}

    public Account(String accountNumber){
        this.accountNumber=accountNumber;
    }
}