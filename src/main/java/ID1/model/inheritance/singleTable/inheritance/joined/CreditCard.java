package ID1.model.inheritance.singleTable.inheritance.joined;

import javax.persistence.Entity;

@Entity

public class CreditCard extends Payment{
    private String ChequeType;

    public String getChequeType() {
        return ChequeType;
    }

    public void setChequeType(String chequeType) {
        ChequeType = chequeType;
    }
    public CreditCard(){}
}
