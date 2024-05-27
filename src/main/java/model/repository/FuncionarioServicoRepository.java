package model.repository;

import model.entity.FuncionarioServicoEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class FuncionarioServicoRepository {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoMensal");

    public FuncionarioServicoEntity create(FuncionarioServicoEntity funcServ) {
        EntityManager em = emf.createEntityManager();

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

    public List<FuncionarioServicoEntity> findByFuncionarioId(Long funcId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FuncionarioServicoEntity> query = em.createQuery("SELECT fs FROM FuncionarioServicoEntity fs WHERE fs.funcionario.id = :funcId", FuncionarioServicoEntity.class);
            query.setParameter("funcId", funcId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    public List<FuncionarioServicoEntity> findByServicoId(Long servId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FuncionarioServicoEntity> query = em.createQuery("SELECT fs FROM FuncionarioServicoEntity fs WHERE fs.servico.id = :servId", FuncionarioServicoEntity.class);
            query.setParameter("servId", servId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
