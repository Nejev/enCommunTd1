package dao.jpa;


import dao.CompteDao;
import modele.Compte;

public class CompteDaoImpl extends AbstractDaoImpl<Compte> implements CompteDao {
    public CompteDaoImpl(){
        this(Compte.class);
    }

    public CompteDaoImpl(Class<Compte> entityClass) {
        super(entityClass);
    }
}
