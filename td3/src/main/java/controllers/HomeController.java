package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import modele.Membre;
import services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/home")
public class HomeController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    private Membre userCo;

    @RequestMapping(value = "/homeS")
    public String homeS(HttpServletRequest request) {
        HttpSession session = request.getSession();
        this.userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
        if (this.userCo != null) {
            request.setAttribute("login", userCo.getLogin());
            return "home";
        } else {
            return "redirect:/login/loginS";
        }
    }

    @RequestMapping(value = "/indexS")
    public String indexS(HttpServletRequest request) {
        return  "index";
    }
}