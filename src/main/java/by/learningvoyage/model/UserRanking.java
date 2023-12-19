package by.learningvoyage.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="rankings")
@Getter
@Setter
public class UserRanking {

    @Id
    Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Subcategory subcategory;

    private int result;

}
