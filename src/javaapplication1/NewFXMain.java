/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javaapplication1;

import javaapplication1.GUI.Controllers.ProduitController;
import javaapplication1.GUI.Controllers.CommandeController;
import Entite.Categorie;
import Entite.Commande;
import Entite.Produit;
import Entite.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.GUI.Controllers.HolderpageController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Plop
 */
public class NewFXMain extends Application {
    
    @Override
public void start(Stage primaryStage) {
    String path="/javaapplication1/GUI/FXMLpages/"; /*must insert in every getResources*/
    
    StackPane window = new StackPane();
    Scene defaultScene=new Scene(window);
    // Detail_commande commadescene=new Detail_commande();
    primaryStage.setTitle("Hello World!");
        System.out.println("holderloader");
    FXMLLoader holderpage=new FXMLLoader(getClass().getResource(path+"Holderpage.fxml"));
        System.out.println("hold");
    FXMLLoader loader2=new FXMLLoader(getClass().getResource(path+"Commande.fxml"));
    primaryStage.setScene(defaultScene);
    /*Declaration PRODRUIT p1-5*/
     Produit p1=new Produit(1,"p1","aeze",new Categorie(1),12,5,"z","Acctive");       
          Produit p2=new Produit(1,"P2","aeze",new Categorie(1),12,5,"z","Acctive");       
     Produit p3=new Produit(1,"P3","aeze",new Categorie(1),12,5,"z","Acctive");       
     Produit p4=new Produit(1,"P4","aeze",new Categorie(1),12,5,"z","Acctive");       
     Produit p5=new Produit(1,"P5","aeze",new Categorie(1),12,5,"z","Acctive");        
     
 StackPane stackpane=new StackPane();
     User user=new User(2);
   Commande commande=new Commande(user,"za",12,"Active");
            commande.setId(3);
              FXMLLoader loader=new FXMLLoader(getClass().getResource(path+"Produit.fxml"));
                       FXMLLoader testerloader=new FXMLLoader(getClass().getResource("testpage.fxml"));
         Parent root; 
            FXMLLoader original=new FXMLLoader(getClass().getResource("FXML.fxml"));

    try {
     
          root=holderpage.load();
          System.out.println("started");
             HolderpageController holderpageController=holderpage.getController();
                      holderpageController.addCommandeNodes();
                      holderpageController.getallproduit();
                stackpane.getChildren().add(root);
                         

    } catch (IOException ex) {
            Logger.getLogger(HolderpageController.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
        Scene  custom=new Scene(stackpane);
       
        primaryStage.setScene(custom);
    primaryStage.show();
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
