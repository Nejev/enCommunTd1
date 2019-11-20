package dao.jpa;

import dao.LivretDao;
import modele.Livret;

public class LivretDaoImpl extends AbstractDaoImpl<Livret> implements LivretDao {
    public LivretDaoImpl(){
        this(Livret.class);
    }

    public LivretDaoImpl(Class<Livret> entityClass) {
        super(entityClass);
    }
}
