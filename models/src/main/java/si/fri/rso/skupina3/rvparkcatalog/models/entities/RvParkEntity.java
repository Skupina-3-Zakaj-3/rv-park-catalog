package si.fri.rso.skupina3.rvparkcatalog.models.entities;

import javax.persistence.*;


@Entity
@Table(name = "parks")
@NamedQueries(value =
        {
                @NamedQuery(name = "RvParkEntity.getAll",
                        query = "SELECT park FROM RvParkEntity park"),
        })
public class RvParkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rv_park_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost_per_day")
    private Float cost_per_day;

    @Column(name = "description")
    private String description;

    public Integer getRv_park_id() {
        return rv_park_id;
    }

    public void setRv_park_id(Integer rv_park_id) {
        this.rv_park_id = rv_park_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCost_per_day() {
        return cost_per_day;
    }

    public void setCost_per_day(Float cost_per_day) {
        this.cost_per_day = cost_per_day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
