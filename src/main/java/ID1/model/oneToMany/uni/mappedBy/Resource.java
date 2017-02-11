package ID1.model.oneToMany.uni.mappedBy;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class Resource {

    @Id
    @GeneratedValue
    Long id;


    private String description;

    @ManyToOne
    private TopicA topic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resource() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TopicA getTopic() {
        return topic;
    }

    public void setTopic(TopicA topic) {
        this.topic = topic;
    }

}
