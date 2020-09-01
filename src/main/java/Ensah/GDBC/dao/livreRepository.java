package Ensah.GDBC.dao;

import Ensah.GDBC.models.livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface livreRepository  extends JpaRepository<livre,Long> {

    @Query("from livre where ISBN =?1 ")
    public livre FindByISBN(String isbn);


    @Query("from livre where auteur is null ")
    public livre FindByAut();
}
