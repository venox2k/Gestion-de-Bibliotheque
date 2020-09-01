package Ensah.GDBC.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "Emprunt" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Emprunt implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdEmprunt ;
    private Date date_emprunt ;
    private Date date_retour ;
    @OneToOne
    @JoinColumn(name="User")
    private User user ;

    @OneToOne
    @JoinColumn(name="livre")
    private livre livre ;
}
