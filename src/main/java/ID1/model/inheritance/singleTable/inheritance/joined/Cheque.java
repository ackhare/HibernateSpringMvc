package ID1.model.inheritance.singleTable.inheritance.joined;

import javax.persistence.Entity;

@Entity

public class Cheque extends Payment{

    private String CreditCardType;

    public String getCreditCardType() {
        return CreditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        CreditCardType = creditCardType;
    }

    public Cheque(){}
}
