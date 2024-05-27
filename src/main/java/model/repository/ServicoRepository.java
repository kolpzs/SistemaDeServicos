package model.repository;

import model.entity.ServicoEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class ServicoRepository implements BasicCrud {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoMensal");

    @Override
    public Object create(Object object) {
        ServicoEntity servico = (ServicoEntity) object;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(servico);
            em.getTransaction().commit();
            return servico;
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

    @Override
    public Object findById(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(ServicoEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Object updateById(Object object) {
        ServicoEntity servico = (ServicoEntity) object;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            servico = em.merge(servico);
            em.getTransaction().commit();
            return servico;
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

    @Override
    public Object delete(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            ServicoEntity servico = em.find(ServicoEntity.class, id);
            if (servico != null) {
                em.remove(servico);
            }
            em.getTransaction().commit();
            return servico;
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

    public List<ServicoEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ServicoEntity> query = em.createQuery("SELECT s FROM ServicoEntity s", ServicoEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
