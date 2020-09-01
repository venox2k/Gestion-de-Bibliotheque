package Ensah.GDBC.models;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;

@Entity
@Table(name = "Livre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class livre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idLivre ;
    private String Titre ;
    private String Description ;
    private String Picture ;
    private String genre ;
    private Long date_sortie ;
    private String ISBN ;

    @OneToOne
    @JoinColumn(name="Auteur")
    private Auteur auteur  ;


}
