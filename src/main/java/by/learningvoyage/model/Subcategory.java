package by.learningvoyage.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity
@Table(name = "subcategories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String subcategoryName;
    private String subcategoryDescription;


    @Lob
    private Blob subcategoryPicture;

    private String accessLevel;

    @OneToMany
    private List<Card> cards;
}
