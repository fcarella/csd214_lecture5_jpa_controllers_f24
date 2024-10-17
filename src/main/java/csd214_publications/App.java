package csd214_publications;


import csd214_publications.entities.*;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
    public void run() {
        EntityManagerFactory emf=null;
        EntityManager em=null;

        try{
            emf= Persistence.createEntityManagerFactory("default");
            em=emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created ("+emf+")");
            em.getTransaction().begin();

            Book book=new Book();
            book.setAuthor("Author");
            book.setCopies(10);
            book.setIsbn10("isbn1234");
            book.setPrice(29.99d);
            book.setTitle("Book Title");
            em.persist(book);
            
            
            Magazine magazine=new Magazine();
            magazine.setCurrIssue(new Date());
            magazine.setCopies(10);
            magazine.setIsbn10("isbn1234");
            magazine.setPrice(29.99d);
            magazine.setTitle("Magazine Title");
            em.persist(magazine);

            
            
            em.getTransaction().commit();
            List<Book> ListOfBooks = em.createQuery("SELECT c FROM Book c").getResultList();
            System.out.println("----------------------------");
            System.out.println("List of Books");
            System.out.println("----------------------------");
            for(Book c:ListOfBooks){
                System.out.println("Title: "+c.getTitle()+" Author:"+c.getAuthor());
            }
            List<Magazine> ListOfMagazines = em.createQuery("SELECT c FROM Magazine c").getResultList();
            System.out.println("----------------------------");
            System.out.println("List of Magazines");
            System.out.println("----------------------------");
            for(Magazine c:ListOfMagazines){
                System.out.println("Title: "+c.getTitle()+" Current Issue:"+c.getCurrIssue());
            }
            List<Publication> ListOfPublications = em.createQuery("SELECT c FROM Publication c").getResultList();
            System.out.println("----------------------------");
            System.out.println("List of Publications");
            System.out.println("----------------------------");
            for(Publication c:ListOfPublications){
                System.out.println("Title: "+c.getTitle());
            }

        }catch(Exception e){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(emf!=null)
                emf.close();
//            if(em!=null)
//                em.close();
        }
    }
}
