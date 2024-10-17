/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csd214_publications_with_controllers.controllers;

import csd214_publications.entities.Publication;
import csd214_publications_with_controllers.controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author fcarella
 */
public class PublicationJpaController implements Serializable {

    public PublicationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publication publication) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(publication);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Publication publication) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            publication = em.merge(publication);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = publication.getId();
                if (findPublication(id) == null) {
                    throw new NonexistentEntityException("The publication with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publication publication;
            try {
                publication = em.getReference(Publication.class, id);
                publication.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publication with id " + id + " no longer exists.", enfe);
            }
            em.remove(publication);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Publication> findPublicationEntities() {
        return findPublicationEntities(true, -1, -1);
    }

    public List<Publication> findPublicationEntities(int maxResults, int firstResult) {
        return findPublicationEntities(false, maxResults, firstResult);
    }

    private List<Publication> findPublicationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Publication as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Publication findPublication(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publication.class, id);
        } finally {
            em.close();
        }
    }

    public int getPublicationCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Publication as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
