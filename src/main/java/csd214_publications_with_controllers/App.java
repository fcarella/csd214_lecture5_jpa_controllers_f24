/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csd214_publications_with_controllers;

import csd214_publications.entities.*;
import csd214_publications_with_controllers.controllers.BookJpaController;
import csd214_publications_with_controllers.controllers.MagazineJpaController;
import java.awt.BorderLayout;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author fcarella
 */
public class App {

    void run() {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("default");
            em = emf.createEntityManager();
            Logger.getLogger(Main.class.getName()).log(Level.INFO, "Entity Manager created (" + emf + ")");
            Book book = new Book();
            book.setAuthor("Author");
            book.setCopies(10);
            book.setIsbn10("isbn1234");
            book.setPrice(29.99d);
            book.setTitle("Book Title");
            Magazine magazine = new Magazine();
            magazine.setCurrIssue(new Date());
            magazine.setCopies(10);
            magazine.setIsbn10("isbn1234");
            magazine.setPrice(29.99d);
            magazine.setTitle("Magazine Title");

            BookJpaController bookController = new BookJpaController(emf);
            MagazineJpaController magazineController = new MagazineJpaController(emf);
            
            bookController.create(book);
            magazineController.create(magazine);
            
            List<Book> books = bookController.findBookEntities();
            List<Magazine> magazines = magazineController.findMagazineEntities();
            for(Book b:books)
                System.out.println(b);
            for(Magazine m:magazines)
                System.out.println(m);
            
            
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
