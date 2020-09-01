package Ensah.GDBC.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username") )
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private String username ;
    private String password ;
    private String Firstname ;
    private  String lastname ;
    private String Email ;
    private Boolean is_admin = false ;
}
