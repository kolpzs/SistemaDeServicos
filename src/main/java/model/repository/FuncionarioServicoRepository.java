package model.repository;

import model.entity.FuncionarioServicoEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class FuncionarioServicoRepository {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoMensal");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FuncionarioServicoEntity create(FuncionarioServicoEntity funcServ) {
        EntityManager em = getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(funcServ);
            em.getTransaction().commit();
            return funcServ;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public FuncionarioServicoEntity findById(Long id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(FuncionarioServicoEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<FuncionarioServicoEntity> findAll() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<FuncionarioServicoEntity> query = em.createQuery("SELECT fs FROM FuncionarioServicoEntity fs", FuncionarioServicoEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
