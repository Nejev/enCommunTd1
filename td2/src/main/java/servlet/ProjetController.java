package servlet;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjetController extends HttpServlet {
    @Autowired
    private ModelService modelService;

    @Autowired
    private ConfigUrlMsgService config;

    @Autowired
    private MapProtectService mapProtectService;

    private String action;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //Passage des urls
            config.setUrlInRequest(request);
            config.setMsgInJsp(request);
            //Construction de l'action
            this.action = request.getPathInfo();
            if (this.action == null) {
                this.action = "";
            } else {
                this.action = this.action.substring(1);
            }
            //Gestion de l'authentification
            HttpSession session = request.getSession();
            Membre userCo = this.modelService.findMembreByLogin((String) session.getAttribute("user_session"));
            if (this.mapProtectService.isProtect(this.action) && userCo == null) {
                response.sendRedirect(config.getURL_GET_LOGIN());
                return;
            }
            //Actions
            switch (this.action) {

                case "projetS":
                    HashMap<String, ArrayList<Projet>> listProjetLogin = this.modelService.dispatchProject(userCo);
                    request.setAttribute("listDirigeP", listProjetLogin.get("responsable"));
                    request.setAttribute("listParticipeP", listProjetLogin.get("participe"));
                    request.setAttribute("listCompetenceP", listProjetLogin.get("competence"));
                    request.setAttribute("listAutreP", listProjetLogin.get("autre"));
                    request.setAttribute("login", userCo.getLogin());
                    request.getRequestDispatcher(config.getPATH_PROJET()).forward(request, response);
                    break;

                case "projetD":
                    String intitulePD = request.getParameter("intituleP");
                    if (intitulePD != null) {
                        if (userCo.getResponsable().contains(this.modelService.findProjetByIntitule(intitulePD))) {
                            this.modelService.deleteProjet(intitulePD);
                            response.sendRedirect(config.getURL_GET_PROJET());
                        } else {
                            request.setAttribute("msgError", "Vous n'êtes pas responsable de ce projet");
                            request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_PROJET())).forward(request, response);
                        }
                    } else {
                        throw new Exception();
                    }
                    break;

                case "projetPQuit":
                    String intituleUQ = request.getParameter("intituleP");
                    if (intituleUQ != null) {
                        this.modelService.quitProjet(userCo, intituleUQ);
                        response.sendRedirect(config.getURL_GET_PROJET());
                    } else {
                        throw new Exception();
                    }
                    break;

                case "projetPParticipe":
                    String intituleUP = request.getParameter("intituleP");
                    if (intituleUP != null) {
                        this.modelService.participeProjet(userCo, intituleUP);
                        response.sendRedirect(config.getURL_GET_PROJET());
                    } else {
                        throw new Exception();
                    }
                    break;

                case "projetP":
                    String intitulePC = request.getParameter("intitule");
                    String descriPC = request.getParameter("description");
                    if (intitulePC != null) {
                        if (this.modelService.createProjet(userCo, intitulePC, descriPC)) {
                            response.sendRedirect(config.getURL_GET_PROJET());
                        } else {
                            request.setAttribute("msgError", "Vous n'êtes pas responsable de ce projet");
                            request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_PROJET())).forward(request, response);
                        }
                    } else {
                        throw new Exception();
                    }
                    break;

                default:
                    request.setAttribute("msgError", "Ressource inconnue");
                    throw new Exception();
            }
        } catch (Exception e) {
            request.setAttribute("msgError", "Il semblerait que votre action a engendrée une erreur");
            request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_ERROR())).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
