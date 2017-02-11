package ID1.model.inheritance.singleTable;

import javax.persistence.*;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "VehicleType",
        discriminatorType = DiscriminatorType.STRING)
public class Vehicle {
    @Id
    @GeneratedValue

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vehicle() {
    }
}
