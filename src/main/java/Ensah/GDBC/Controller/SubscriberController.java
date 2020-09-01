package Ensah.GDBC.Controller;


import Ensah.GDBC.Services.LivreService;
import Ensah.GDBC.dao.AuteurRepository;
import Ensah.GDBC.dao.EmpruntRepository;
import Ensah.GDBC.dao.UserRepository;
import Ensah.GDBC.dao.livreRepository;
import Ensah.GDBC.models.Emprunt;
import Ensah.GDBC.models.User;
import Ensah.GDBC.models.livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class SubscriberController {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private LivreService livreService ;

    @Autowired
    private EmpruntRepository empruntRepository;

    @Autowired
    private livreRepository livrerepository ;

    @Autowired
    private AuteurRepository auteurRepository ;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

    @GetMapping("/ShowLivre")
    public String ShowLivre(@RequestParam("livreId") String isbn, Model model )
    {
        livre book = livreService.getLivre(isbn);
        model.addAttribute("livre",book);
        String username ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);

        boolean exist ;
        boolean ad  ;
        if(user.getIs_admin()==true)
        {
            ad=true ;
        }
        else
        {
            ad=false ;
        }
        if(empruntRepository.FindbyUaL(user,livreService.getLivre(isbn))!=null)
        {
            exist =true;
        }else
        {
            exist=false ;

        }
        model.addAttribute("ad",ad);

        model.addAttribute("existe",exist);

        return "Book";


    }

    @RequestMapping("/Emprunter")
    public String Emprunter(@RequestParam("livreis") String isbn ,Model model)
    {
        Emprunt emp = new Emprunt();
        emp.setDate_emprunt(new Date());
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime test =  today.plusDays(7);

        java.sql.Date date = java.sql.Date.valueOf(test.toLocalDate());
        java.util.Date utilDate = new java.util.Date(date.getTime());
        emp.setDate_retour(utilDate);
        String username ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        emp.setUser(user);
        emp.setLivre(livreService.getLivre(isbn));
        if(empruntRepository.FindbyUaL(user,livreService.getLivre(isbn))==null)
        {

            empruntRepository.save(emp);

        }

        return "redirect:/home";

    }


    @RequestMapping("/Emprunts")
    public String showEmp(Model model)
    {
        String username ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);
        List<Emprunt> emps = empruntRepository.FindByU(user);

        model.addAttribute("emps",emps);

        return "EmpsSub";

    }

}
