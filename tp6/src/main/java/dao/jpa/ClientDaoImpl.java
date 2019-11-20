package dao.jpa;

import dao.ClientDao;
import modele.Client;

public class ClientDaoImpl extends AbstractDaoImpl<Client> implements ClientDao {
    public ClientDaoImpl() {
        this(Client.class);
    }
    public ClientDaoImpl(Class<Client> entityClass) {
        super(entityClass);
    }
}
