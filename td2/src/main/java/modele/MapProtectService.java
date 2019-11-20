package modele;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class MapProtectService {

    private ArrayList<String> needConnection;

    public MapProtectService() {
        this.needConnection = new ArrayList<>();
        this.needConnection.add("homeS");
        this.needConnection.add("competenceS");
        this.needConnection.add("competenceP");
        this.needConnection.add("competenceMembreP");
        this.needConnection.add("competenceMembreD");
        this.needConnection.add("projetS");
        this.needConnection.add("projetD");
        this.needConnection.add("projetPQuit");
        this.needConnection.add("projetPParticipe");
        this.needConnection.add("projetP");
    }

    public ArrayList<String> getNeedConnection() {
        return needConnection;
    }

    public void setNeedConnection(ArrayList<String> needConnection) {
        this.needConnection = needConnection;
    }

    public boolean isProtect(String action) {
        return this.needConnection.contains(action);
    }
}
