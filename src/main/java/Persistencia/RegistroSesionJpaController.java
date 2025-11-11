package Persistencia;

import Logica.RegistroSesion;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RegistroSesionJpaController implements Serializable {

    public RegistroSesionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public RegistroSesionJpaController() {
        this.emf = Persistence.createEntityManagerFactory("CulturartePU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroSesion registroSesion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(registroSesion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroSesion registroSesion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            registroSesion = em.merge(registroSesion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = registroSesion.getId();
                if (findRegistroSesion(id) == null) {
                    throw new NonexistentEntityException("The registroSesion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroSesion registroSesion;
            try {
                registroSesion = em.getReference(RegistroSesion.class, id);
                registroSesion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroSesion with id " + id + " no longer exists.", enfe);
            }
            em.remove(registroSesion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroSesion> findRegistroSesionEntities() {
        return findRegistroSesionEntities(true, -1, -1);
    }

    public List<RegistroSesion> findRegistroSesionEntities(int maxResults, int firstResult) {
        return findRegistroSesionEntities(false, maxResults, firstResult);
    }

    private List<RegistroSesion> findRegistroSesionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroSesion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public RegistroSesion findRegistroSesion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroSesion.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroSesionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroSesion> rt = cq.from(RegistroSesion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public void limpiarRegistrosAntiguos() {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();

            // Eliminar los que son de hace más de 30 días
            em.createQuery("DELETE FROM RegistroSesion r WHERE r.fechaRegistro < :fecha")
              .setParameter("fecha", java.time.LocalDateTime.now().minusDays(30))
              .executeUpdate();

            //Eliminar los más antiguos si hay más de 10.000
            List<Integer> ids = em.createQuery(
                "SELECT r.id FROM RegistroSesion r ORDER BY r.fechaRegistro DESC", Integer.class)
                .setFirstResult(10000) // a partir del 10.001
                .getResultList();

            if (!ids.isEmpty()) {
                em.createQuery("DELETE FROM RegistroSesion r WHERE r.id IN :ids")
                  .setParameter("ids", ids)
                  .executeUpdate();
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
