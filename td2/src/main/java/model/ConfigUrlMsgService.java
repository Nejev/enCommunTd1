package model;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

@Service

public class ConfigUrlMsgService {
    private String URL_BASE_APPLI = "/td2_war";

    private String URL_BASE_MODULE_COMPETENCE = URL_BASE_APPLI + "/competence";
    private String URL_BASE_MODULE_ERROR = URL_BASE_APPLI + "/error";
    private String URL_BASE_MODULE_HOME = URL_BASE_APPLI + "/home";
    private String URL_BASE_MODULE_LOGIN = URL_BASE_APPLI + "/login";
    private String URL_BASE_MODULE_PROJET = URL_BASE_APPLI + "/projet";
    private String URL_BASE_MODULE_REGISTER = URL_BASE_APPLI + "/register";

    private String URL_GET_COMPETENCE = URL_BASE_MODULE_COMPETENCE + "/competenceS";
    private String URL_POST_COMPETENCE = URL_BASE_MODULE_COMPETENCE + "/competenceP";
    private String URL_POST_COMPETENCEMEMBRE = URL_BASE_MODULE_COMPETENCE + "/competenceMembreP";
    private String URL_DELETE_COMPETENCEMEMBRE = URL_BASE_MODULE_COMPETENCE + "/competenceMembreD";
    private String URL_GET_ERROR = URL_BASE_MODULE_ERROR + "/errorS";
    private String URL_GET_HOME = URL_BASE_MODULE_HOME + "/homeS";
    private String URL_GET_LOGIN = URL_BASE_MODULE_LOGIN + "/loginS";
    private String URL_POST_LOGIN = URL_BASE_MODULE_LOGIN + "/loginP";
    private String URL_DELETE_LOGIN = URL_BASE_MODULE_LOGIN + "/loginD";
    private String URL_GET_PROJET = URL_BASE_MODULE_PROJET + "/projetS";
    private String URL_POST_PROJET = URL_BASE_MODULE_PROJET + "/projetP";
    private String URL_POST_QUIT_PROJET = URL_BASE_MODULE_PROJET + "/projetPQuit";
    private String URL_POST_PARTICIPE_PROJET = URL_BASE_MODULE_PROJET + "/projetPParticipe";
    private String URL_DELETE_PROJET = URL_BASE_MODULE_PROJET + "/projetD";
    private String URL_GET_REGISTER = URL_BASE_MODULE_REGISTER + "/registerS";
    private String URL_POST_REGISTER = URL_BASE_MODULE_REGISTER + "/registerP";

    private String PATH_ERROR = "/WEB-INF/view/error.jsp";
    private String PATH_HOME = "/WEB-INF/view/home.jsp";
    private String PATH_LOGIN = "/WEB-INF/view/login.jsp";
    private String PATH_PROJET = "/WEB-INF/view/projet.jsp";
    private String PATH_COMPETENCE = "/WEB-INF/view/competence.jsp";
    private String PATH_REGISTER = "/WEB-INF/view/register.jsp";

    public String getURL_BASE_APPLI() {
        return URL_BASE_APPLI;
    }

    public String getURL_BASE_MODULE_COMPETENCE() {
        return URL_BASE_MODULE_COMPETENCE;
    }

    public String getURL_BASE_MODULE_ERROR() {
        return URL_BASE_MODULE_ERROR;
    }

    public String getURL_BASE_MODULE_HOME() {
        return URL_BASE_MODULE_HOME;
    }

    public String getURL_BASE_MODULE_LOGIN() {
        return URL_BASE_MODULE_LOGIN;
    }

    public String getURL_BASE_MODULE_PROJET() {
        return URL_BASE_MODULE_PROJET;
    }

    public String getURL_BASE_MODULE_REGISTER() {
        return URL_BASE_MODULE_REGISTER;
    }

    public String getURL_GET_COMPETENCE() {
        return URL_GET_COMPETENCE;
    }

    public String getURL_POST_COMPETENCE() {
        return URL_POST_COMPETENCE;
    }

    public String getURL_POST_COMPETENCEMEMBRE() {
        return URL_POST_COMPETENCEMEMBRE;
    }

    public String getURL_DELETE_COMPETENCEMEMBRE() {
        return URL_DELETE_COMPETENCEMEMBRE;
    }

    public String getURL_GET_ERROR() {
        return URL_GET_ERROR;
    }

    public String getURL_GET_HOME() {
        return URL_GET_HOME;
    }

    public String getURL_GET_LOGIN() {
        return URL_GET_LOGIN;
    }

    public String getURL_POST_LOGIN() {
        return URL_POST_LOGIN;
    }

    public String getURL_DELETE_LOGIN() {
        return URL_DELETE_LOGIN;
    }

    public String getURL_GET_PROJET() {
        return URL_GET_PROJET;
    }

    public String getURL_POST_PROJET() {
        return URL_POST_PROJET;
    }

    public String getURL_POST_QUIT_PROJET() {
        return URL_POST_QUIT_PROJET;
    }

    public String getURL_POST_PARTICIPE_PROJET() {
        return URL_POST_PARTICIPE_PROJET;
    }

    public String getURL_DELETE_PROJET() {
        return URL_DELETE_PROJET;
    }

    public String getURL_GET_REGISTER() {
        return URL_GET_REGISTER;
    }

    public String getURL_POST_REGISTER() {
        return URL_POST_REGISTER;
    }

    public String getPATH_ERROR() {
        return PATH_ERROR;
    }

    public String getPATH_HOME() {
        return PATH_HOME;
    }

    public String getPATH_LOGIN() {
        return PATH_LOGIN;
    }

    public String getPATH_PROJET() {
        return PATH_PROJET;
    }

    public String getPATH_COMPETENCE() {
        return PATH_COMPETENCE;
    }

    public String getPATH_REGISTER() {
        return PATH_REGISTER;
    }

    public void setUrlInRequest(HttpServletRequest request) {
        request.setAttribute("URL_BASE_APPLI", URL_BASE_APPLI);
        request.setAttribute("URL_GET_HOME", URL_GET_HOME);
        request.setAttribute("URL_POST_LOGIN", URL_POST_LOGIN);
        request.setAttribute("URL_DELETE_LOGIN", URL_DELETE_LOGIN);
        request.setAttribute("URL_GET_PROJET", URL_GET_PROJET);
        request.setAttribute("URL_DELETE_PROJET", URL_DELETE_PROJET);
        request.setAttribute("URL_POST_PROJET", URL_POST_PROJET);
        request.setAttribute("URL_UPDATE_QUIT_PROJET", URL_POST_QUIT_PROJET);
        request.setAttribute("URL_UPDATE_PARTICIPE_PROJET", URL_POST_PARTICIPE_PROJET);
        request.setAttribute("URL_GET_COMPETENCE", URL_GET_COMPETENCE);
        request.setAttribute("URL_POST_COMPETENCE", URL_POST_COMPETENCE);
        request.setAttribute("URL_POST_COMPETENCEMEMBRE", URL_POST_COMPETENCEMEMBRE);
        request.setAttribute("URL_DELETE_COMPETENCEMEMBRE", URL_DELETE_COMPETENCEMEMBRE);
        request.setAttribute("URL_GET_ERROR", URL_GET_ERROR);
        request.setAttribute("URL_GET_REGISTER", URL_GET_REGISTER);
        request.setAttribute("URL_POST_REGISTER", URL_POST_REGISTER);
    }

    public void setMsgInJsp(HttpServletRequest request) {
        //Gestion de l'affichage d'un msg
        if (request.getAttribute("msgError") != null) {
            request.setAttribute("msg", request.getAttribute("msgError"));
            request.setAttribute("colorMsg", "red");
        } else if (request.getAttribute("msgValide") != null) {
            request.setAttribute("msg", request.getAttribute("msgValide"));
            request.setAttribute("colorMsg", "green");
        } else if (request.getAttribute("msgAutre") != null) {
            request.setAttribute("msg", request.getAttribute("msgAutre"));
            request.setAttribute("colorMsg", "black");
        } else {
            request.setAttribute("msg", " ");
            request.setAttribute("colorMsg", "black");
        }
    }

    public String cutBaseUrl(String url){
        return url.substring(URL_BASE_APPLI.length());
    }
}
