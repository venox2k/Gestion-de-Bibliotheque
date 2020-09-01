package Ensah.GDBC.Controller;

import Ensah.GDBC.Services.LivreService;
import Ensah.GDBC.dao.AuteurRepository;
import Ensah.GDBC.dao.EmpruntRepository;
import Ensah.GDBC.dao.UserRepository;
import Ensah.GDBC.dao.livreRepository;
import Ensah.GDBC.models.Auteur;
import Ensah.GDBC.models.Emprunt;
import Ensah.GDBC.models.User;
import Ensah.GDBC.models.livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class ViewController {

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



    //--------------login---------------------------------------
    @RequestMapping("/login")
    public String showHome()
    {
        return "login";
    }
    @RequestMapping("/home")
    public String show(Model model ) {
        List<livre>  livres = livreService.ConsulterLivres();

        String username ;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findByUsername(username);

        model.addAttribute("username", user.getUsername());
        if(user.getIs_admin()==true)
        {
            model.addAttribute("livres",livres);

            return"Admin";
        }
        else
        {

        model.addAttribute("livres",livres);
        return "home";
        }
    }

    //-------------logout----------------------------------------
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
    //----------------------------Rgister-------------------------
    @RequestMapping("/SignUp")
    public String Show(Model model)
    {
        User acco =  new User();
        model.addAttribute("user",acco);
        return "SignUp";
    }

    @RequestMapping("/Register")
    public  String signup(@ModelAttribute("user") User user ){
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepository.save(user);
                return "redirect:/login";
    }




}
