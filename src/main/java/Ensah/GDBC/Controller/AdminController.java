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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

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

    public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/images/Livres";


    @RequestMapping("/AllEmprunts")
    public String showAllEmp(Model model)
    {

        List<Emprunt> emps = empruntRepository.findAll();

        model.addAttribute("emps",emps);

        return "EmpsAdmin";

    }


    @RequestMapping("/AllUsers")
    public String ShowAllUsers(Model model)
    {
        List<User> users = userRepository.findAll();

        model.addAttribute("users",users);

        return "UsersAdmin";
    }


    @RequestMapping("/DeleteUser")
    public String DeleteUser(@RequestParam("UserId") String username)
    {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);


        return "redirect:/AllUsers";
    }


    @RequestMapping("/DeleteLivre")
    public String DeleteBook(@RequestParam("livreId") String isbn)
    {
        livre liv = livreService.getLivre(isbn);
        livreService.DeleteLivre(liv);

        return "redirect:/home";
    }



    @RequestMapping("/AddBook")
    public String Addbook(Model model)
    {
        livre test = new livre();
        model.addAttribute("book",test);

        return"AddBook";
    }

    @RequestMapping("/AddBooks")
    public  String AddBook(@ModelAttribute("book") livre liv,Model model,@RequestParam("files") MultipartFile files)
    {
        Path fileNameAndPath = Paths.get(uploadDirectory, files.getOriginalFilename());
        liv.setPicture(files.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, files.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        livrerepository.save(liv);
        model.addAttribute("aut",new Auteur());

        return "AddAuteur" ;
    }

    @RequestMapping("/AddAuteur")
    public  String AddAut(@ModelAttribute("aut") Auteur aut )
    {
        livre lik = livrerepository.FindByAut();
        System.out.println(lik.toString());
        lik.setAuteur(aut);
        auteurRepository.save(aut);
        livrerepository.save(lik);

        return "redirect:/home" ;
    }

}
