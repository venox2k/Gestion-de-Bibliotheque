package Ensah.GDBC.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Auteur" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Auteur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idAuteur ;
    private String FirstName ;
    private String LastName ;
}
