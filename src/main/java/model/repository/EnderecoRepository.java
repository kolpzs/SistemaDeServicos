package model.repository;

import model.entity.ClienteEntity;
import model.entity.EnderecoEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class EnderecoRepository implements BasicCrud {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoMensal");

    @Override
    public Object create(Object object) {
        EnderecoEntity endereco = (EnderecoEntity) object;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(endereco);
            em.getTransaction().commit();
            return endereco;
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
            return em.find(EnderecoEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Object updateById(Object object) {
        EnderecoEntity endereco = (EnderecoEntity) object;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            endereco = em.merge(endereco);
            em.getTransaction().commit();
            return endereco;
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
            EnderecoEntity endereco = em.find(EnderecoEntity.class, id);
            if (endereco != null) {
                em.remove(endereco);
            }
            em.getTransaction().commit();
            return endereco;
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

    public List<EnderecoEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EnderecoEntity> query = em.createQuery("SELECT e FROM EnderecoEntity e", EnderecoEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }

    public List<EnderecoEntity> findByCliente(ClienteEntity cliente) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<EnderecoEntity> query = em.createQuery("SELECT e FROM EnderecoEntity e WHERE e.cliente = :cliente", EnderecoEntity.class);
            query.setParameter("cliente", cliente);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
