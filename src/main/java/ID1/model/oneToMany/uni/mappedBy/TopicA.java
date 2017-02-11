package ID1.model.oneToMany.uni.mappedBy;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "topic_ab", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
public class TopicA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public TopicA(String name, Date lastUpdated, Date dateCreated, Set<Resource> resourceSet) {

        this.name = name;

        this.resourceSet = resourceSet;
    }

    public Set<Resource> getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(Set<Resource> resourceSet) {
        this.resourceSet = resourceSet;
    }

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "topic")
    private Set<Resource> resourceSet;


}
