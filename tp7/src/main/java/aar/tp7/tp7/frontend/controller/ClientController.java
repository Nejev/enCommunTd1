package aar.tp7.tp7.frontend.controller;

import aar.tp7.tp7.backend.modele.Client;
import aar.tp7.tp7.backend.service.BanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;

@Controller
@RequestMapping("/clients")
public class ClientController extends HttpServlet {
    @Autowired
    private BanqueService banqueservice;

    @GetMapping("/clientList")
    public String clientList(Model model){
        model.addAttribute("clients",banqueservice.findAllClient());
        return "clientList";
    }

    @PostMapping("/clientList")
    public String choixClient(@RequestParam long idclient, RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("idclient",idclient);
        return "redirect:/clients/client";
    }

    @GetMapping("/client")
    public String client(@RequestParam long idclient, Model model){
        Client client = banqueservice.findClient(idclient);
        if(client != null){
            model.addAttribute("client", client);
            return "client";
        }else{
            return "redirect:/clients/client";
        }
    }
}
