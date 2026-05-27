package net.codeclass.codeclassbackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "problems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Problem {

    @Id
    private Long id;

    private String title;
    private int solved;
    private int submissions;
    private String rate;

    @Column(length = 2000)
    private String description;

    @Column(length = 1000)
    private String inputDesc;

    @Column(length = 1000)
    private String outputDesc;

    @Column(length = 2000)
    private String exampleInput;

    @Column(length = 1000)
    private String exampleOutput;
}
