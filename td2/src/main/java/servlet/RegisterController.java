package servlet;

import model.ConfigUrlMsgService;
import model.MapProtectService;
import model.Membre;
import model.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterController extends HttpServlet {
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
                case "registerS":
                    request.getRequestDispatcher(config.getPATH_REGISTER()).forward(request, response);
                    break;

                case "registerP":
                    String loginP = request.getParameter("login");
                    String passwordP = request.getParameter("password");
                    String surnomP = request.getParameter("surnom");
                    if (loginP != null && passwordP != null && surnomP != null) {
                        if (this.modelService.createMembre(loginP, passwordP, surnomP)) {
                            response.sendRedirect(config.getURL_GET_LOGIN());
                        } else {
                            request.setAttribute("msgError", "Login déja utilisé ou champs mal renseigné");
                            request.getRequestDispatcher(config.cutBaseUrl(config.getURL_GET_REGISTER())).forward(request, response);
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
