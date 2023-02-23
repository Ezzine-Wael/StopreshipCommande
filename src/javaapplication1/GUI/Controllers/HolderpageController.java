/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaapplication1.GUI.Controllers;

import Entite.Commande;
import Entite.Detail_Commande;
import Entite.Produit;
import Entite.Store;
import Entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication1.Service.Service.ServiceProduit;
import javaapplication1.Service.Service.Service_Commande;
import javaapplication1.Service.Service.Service_Detail_Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class HolderpageController implements Initializable {

    @FXML
    private AnchorPane mainholder;
    @FXML
    private VBox commandeContainer;
    @FXML
    private VBox produit_container;
    String path="/javaapplication1/GUI/FXMLpages/"; /*must insert in every getResources*/
private static HolderpageController instance;
private static boolean started=false;
    private static HolderpageController getHolderpageController(){
        return instance;
    }
    @FXML
    private Button shop;
    @FXML
    private AnchorPane affichage;
    @FXML
    private Button historique;
    @FXML
    private Button changeuser;
    @FXML
    private VBox shopproduit;
    @FXML
    private AnchorPane shoppingdisplay;
    private static List<Detail_Commande> paniercourrant=new ArrayList<>();
    private Commande commande=new Commande();
    @FXML
    private VBox Paniercourrante;
    @FXML
    private Button ConfirmePanier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        instance=this;
        if (!started){
            shoppingdisplay.setVisible(false);
            started=true;
        }
    }    
     public void addCommandeNodes() {
         Service_Commande se=new Service_Commande();
                     
      User  u=new User(1);
       List<Commande> list=se.readAll();
        for (Commande c:list) {
            try {
                System.out.println(c);
                FXMLLoader commandeLoader = new FXMLLoader(getClass().getResource(path + "Commande.fxml"));
                System.out.println(commandeLoader.getLocation());
                Node node = commandeLoader.load();
             CommandeController cc=commandeLoader.getController();
               cc.setCommande(c);
              commandeContainer.getChildren().add(node);
            } catch (IOException ex) {
            Logger.getLogger(HolderpageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /* private void addProduitNodes(Commande c) {
        for (int i = 0; i < count; i++) {
            try {
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource(path + "Produit.fxml"));
                Node node = produitLoader.load();
                mainholder2.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }*/
    
    public void populateproduit(List<Detail_Commande> listproduit){
        // add list produit in produit_container  using the same instance as Commandepanier
       instance.produit_container.getChildren().clear();
        for (Detail_Commande c:listproduit) {
            try {
                
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource(path + "Detail_commande.fxml"));
                System.out.println(produitLoader.getLocation());
                Node node = produitLoader.load();
              Detail_commandeController detail_commande=produitLoader.getController();
                detail_commande.setDetail_Produit(c);
           
                instance.produit_container.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
      
       /* try {
                
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource(path + "Produit.fxml"));
                System.out.println(produitLoader.getLocation());
                Node node = produitLoader.load();
               ProduitController produitController=produitLoader.getController();
                produitController.Setdetail();
                instance.produit_container.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex);
            }
         try {
                
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource(path + "Produit.fxml"));
                System.out.println(produitLoader.getLocation());
                Node node = produitLoader.load();
               ProduitController produitController=produitLoader.getController();
                produitController.Setdetail();
                instance.produit_container.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex);
            }*/
    }
    


    @FXML
    private void confirmerpanier(ActionEvent event) {
        // must check if paniercourrant is empty or not
        if (!paniercourrant.isEmpty())
        {
         // reset the display
       commandeContainer.getChildren().clear();
       //setting the commande structure and initialising the services
       Service_Commande sc=new Service_Commande();
       Service_Detail_Commande sdc=new Service_Detail_Commande();
       User u=new User(1);
       commande.setUser(u);
       commande=sc.insertCommande(commande);
       // looping over paniercourrant to insert all detail_concerning them intoDB
       for (Detail_Commande dc:paniercourrant)
       {    
           dc.setCommande(commande);
           // calcule store depends du store    integre rayen
           Store store=new Store(1);
           dc.setStore(store);
           sdc.insert(dc);
       }
       addCommandeNodes();
       // empty panier display
       Paniercourrante.getChildren().clear();
       // reseting current panier
       paniercourrant.clear();
    }
    }

    @FXML
    private void displayHistorique(ActionEvent event) {
       commandeContainer.getChildren().clear();
            System.out.println("Clicekd");
            shoppingdisplay.setVisible(false);
            affichage.setVisible(true);
                   addCommandeNodes();

           
    }

    @FXML
    private void ChangeUser(ActionEvent event) {
    }
    public void getallproduit(){
        shopproduit.getChildren().clear();
        ServiceProduit produit=new ServiceProduit();
        List<Produit> list=produit.readAll();
        System.out.println(list);
         for (Produit p:list) {
            try {
                System.out.println("test");
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource(path + "Produit.fxml"));
                System.out.println(produitLoader.getLocation());
                Node node = produitLoader.load();
             ProduitController cc=produitLoader.getController();
               cc.setDetail_Produit(p);
              shopproduit.getChildren().add(node);
            } catch (IOException ex) {
            Logger.getLogger(HolderpageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }}

    @FXML
    private void openshop(ActionEvent event) {
        //switch nodes to displaying shop and make historque invisible
         System.out.println("Clicekd");
        shoppingdisplay.setVisible(true);
        affichage.setVisible(false);
        instance.getallproduit();
    }
    public void ajouterproduitpanierlist(Produit p,int quantite){
        //intiliating services
        Detail_Commande dc=new Detail_Commande();
        //testing phase integre  must change User to automatique getter
        Store store=new Store(1,"Tunis","aaaaa");
        User user=new User(1);
        // setting user on commande
        commande.setUser(user);
        //setting destination on commande     default TUNIS for now 
        // integre must change destination input
        commande.setDestination("Tunis");
        // converting produit into the correct format for Detail_commande to insert into DB
        Detail_Commande.convertProduitToDetail(dc,p,store ,quantite , commande);
        //boolean for checking if the product already exist in panier or no
        boolean added=false;
        System.out.println(" Panier courrant ------------"+paniercourrant);
        for(Detail_Commande detail:paniercourrant)
        {      
            System.out.println("Comparison:::::::::::::: "+detail.getProduit().getId()+" == "+p.getId());
            if (detail.getProduit().getId()==p.getId()){
                System.out.println("same Object");
                detail.setQuantite(detail.getQuantite()+quantite);
                added=true;
                System.out.println("added ========"+added);
                break;
            }
            
        }
        // adding product if product does not exist in panier
        if(!added)
            {
                System.out.println("Different Object");
                        paniercourrant.add(dc);

            }

         // integre   Commande.update(commande) updating currect commande with proper value;
         // commande must have prix , etat is default to pending  or nonPayer
           updatepanierdisplay(paniercourrant);
        }
        // update current panier 
    public void updatepanierdisplay(List<Detail_Commande> paniercourrant){
        instance.Paniercourrante.getChildren().clear();
                      
        for (Detail_Commande c:paniercourrant) {
            try {
                FXMLLoader produitLoader = new FXMLLoader(getClass().getResource(path + "Detail_commande.fxml"));
                Node node = produitLoader.load();

                Detail_commandeController detail_commande=produitLoader.getController();
                detail_commande.setDetail_Produit(c);

              instance.Paniercourrante.getChildren().add(node);
            } catch (IOException ex) {
                System.out.println(ex);
            }
    }
    }

    
}

