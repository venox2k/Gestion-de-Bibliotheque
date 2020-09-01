package Ensah.GDBC.Services;

import Ensah.GDBC.dao.livreRepository;
import Ensah.GDBC.models.livre;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    private livreRepository livreRepo ;

    @Autowired
    public LivreService(livreRepository livreRepo)
    {
        this.livreRepo=livreRepo;
    }

    public List<livre> ConsulterLivres()
    {
        List<livre> livres = livreRepo.findAll();
        System.out.println("livre :" + livres);
        return livres ;
    }


    public livre getLivre(String isbn)
    {
        return livreRepo.FindByISBN(isbn);
    }


    public void DeleteLivre(livre l)
    {
        livreRepo.delete(l);
    }
}
