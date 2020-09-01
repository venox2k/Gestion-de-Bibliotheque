package Ensah.GDBC.dao;

import Ensah.GDBC.models.Emprunt;
import Ensah.GDBC.models.User;
import Ensah.GDBC.models.livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt,Long> {

    @Query("from Emprunt where user =?1 and livre=?2")
    public Emprunt FindbyUaL(User UId , livre Lid );


    @Query("from Emprunt where user =?1 ")
    public List<Emprunt> FindByU(User us);
}
