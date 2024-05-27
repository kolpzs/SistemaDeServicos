package model.repository;

import model.entity.UsuarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class UsuarioRepository implements BasicCrud{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoMensal");

    @Override
    public Object create(Object object) {
        UsuarioEntity usuario = (UsuarioEntity) object;

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return usuario;
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
            return em.find(UsuarioEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Object updateById(Object object) {
        UsuarioEntity usuario = (UsuarioEntity) object;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            return usuario;
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
            UsuarioEntity usuario = em.find(UsuarioEntity.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
            return usuario;
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

    public Object findByUsername(String username) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<UsuarioEntity> query = em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.username = :username", UsuarioEntity.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<UsuarioEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<UsuarioEntity> query = em.createQuery("SELECT u FROM UsuarioEntity u", UsuarioEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
