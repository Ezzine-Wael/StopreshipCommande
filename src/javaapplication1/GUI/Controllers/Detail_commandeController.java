/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaapplication1.GUI.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javaapplication1.Service.Service.Service_Detail_Commande;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import Entite.Detail_Commande;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class Detail_commandeController implements Initializable {

    @FXML
    private Label quantite;
    @FXML
    private Label prix;
    @FXML
    private Label etat;
    @FXML
    private Label ProduitNom;
    String path="/javaapplication1/GUI/FXMLpages/"; /*must insert in every getResources*/
    @FXML
    private Label quantite1;
    @FXML
    private Label prix1;
    @FXML
    private Label ProduitNom1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
    }    
    public void setDetail_Produit(Detail_Commande p){
        System.out.println(p);
          ProduitNom.setText(String.valueOf(p.getId()));
        prix.setText(String.valueOf(p.getPrix_total()));
        quantite.setText(String.valueOf(p.getQuantite()));
        etat.setText(String.valueOf(p.getEtat()));      
    }
    
 
    
    
}
