package by.learningvoyage.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String firstAnswer;
    private String secondAnswer;
    private String thirdAnswer;
    private String fourthAnswer;


    @Transient
    @Valid
    @Min(value = 1)
    @Max(value = 4)
    private Integer correctAnswerId;

    private String correctAnswer;
}
