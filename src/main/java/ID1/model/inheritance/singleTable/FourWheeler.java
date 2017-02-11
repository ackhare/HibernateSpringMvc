package ID1.model.inheritance.singleTable;

import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("CAR")

public class FourWheeler extends Vehicle{
    private String steeringWheel;

    public String getSteeringWheel() {
        return steeringWheel;
    }

    public void setSteeringWheel(String steeringWheel) {
        this.steeringWheel = steeringWheel;
    }
    public FourWheeler(){

    }
}
