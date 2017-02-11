package ID1.model.oneToMany.uni;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "ForeignKeyAssoAccountEntityTRY")
@Table(name = "AccountEntity", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID")})
public class AccountEntity implements Serializable
{

    private static final long serialVersionUID = -6790693372846798580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer accountId;

    @Column(name = "ACC_NUMBER", unique = true, nullable = false, length = 100)
    private String accountNumber;

    @Column(name = "deposit", unique = false, nullable = false)
    private int deposit;

    @ManyToOne
    private EmployeeEntity employee;

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

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

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
    public AccountEntity(){

    }
    public AccountEntity(String accountNumber,int deposit){
        this.accountNumber=accountNumber;
        this.deposit=deposit;
    }
}