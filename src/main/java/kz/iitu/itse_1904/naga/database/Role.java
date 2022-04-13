package kz.iitu.itse_1904.naga.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long     id;

    public Role(long id) {
        this.id = id;
    }

    public Role() {
    }

    String value;

}
