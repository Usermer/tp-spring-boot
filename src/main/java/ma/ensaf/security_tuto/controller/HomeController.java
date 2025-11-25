package ma.ensaf.security_tuto.controller;
import ma.ensaf.security_tuto.repository.ProduitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ProduitRepository produitRepository;

    public HomeController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "home"; // home.html
    }
}
