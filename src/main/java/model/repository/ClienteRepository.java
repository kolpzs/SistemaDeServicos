package model.repository;

import model.entity.ClienteEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class ClienteRepository implements BasicCrud{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoMensal");

    @Override
    public Object create(Object object) {
        ClienteEntity cliente = (ClienteEntity) object;

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
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
            return em.find(ClienteEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Object updateById(Object object) {
        ClienteEntity cliente = (ClienteEntity) object;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.getTransaction().commit();
            return cliente;
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
            ClienteEntity cliente = em.find(ClienteEntity.class, id);
            if (cliente != null) {
                em.remove(cliente);
            }
            em.getTransaction().commit();
            return cliente;
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

    public Object findByNome(String nome) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<ClienteEntity> query = em.createQuery("SELECT c FROM ClienteEntity c WHERE c.nome = :nome", ClienteEntity.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close(); // Fechar o EntityManager para liberar recursos
        }
    }

    public List<ClienteEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<ClienteEntity> query = em.createQuery("SELECT c FROM ClienteEntity c", ClienteEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
