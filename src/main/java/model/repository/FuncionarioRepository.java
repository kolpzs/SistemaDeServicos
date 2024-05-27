package model.repository;

import model.entity.FuncionarioEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class FuncionarioRepository implements BasicCrud{
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoMensal");

    @Override
    public Object create(Object object) {
        FuncionarioEntity funcionario = (FuncionarioEntity) object;

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(funcionario);
            em.getTransaction().commit();
            return funcionario;
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
            return em.find(FuncionarioEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Object updateById(Object object) {
        FuncionarioEntity funcionario = (FuncionarioEntity) object;
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            funcionario = em.merge(funcionario);
            em.getTransaction().commit();
            return funcionario;
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
            FuncionarioEntity funcionario = em.find(FuncionarioEntity.class, id);
            if (funcionario != null) {
                em.remove(funcionario);
            }
            em.getTransaction().commit();
            return funcionario;
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
            TypedQuery<FuncionarioEntity> query = em.createQuery("SELECT f FROM FuncionarioEntity f WHERE f.nome = :nome", FuncionarioEntity.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close(); // Fechar o EntityManager para liberar recursos
        }
    }

    public List<FuncionarioEntity> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<FuncionarioEntity> query = em.createQuery("SELECT f FROM FuncionarioEntity f", FuncionarioEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            em.close();
        }
    }
}
