/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaapplication1.GUI.Controllers;

import Entite.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class ProduitController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prix;
    private Produit produit;
    @FXML
    private TextField quantite;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
       public void setDetail_Produit(Produit p){
          produit=p;
          nom.setText(String.valueOf(p.getId()));
        prix.setText(String.valueOf(p.getPrix()));
      
    }
       public void Setdetail(){
             nom.setText("Nomproduit");
           System.out.println("setting Prix");
        prix.setText("extraprix");
       }

    @FXML
    private void ajouterproduitaupanier(ActionEvent event) {
        
        HolderpageController holderpagecontroller=new HolderpageController();
        holderpagecontroller.ajouterproduitpanierlist(produit,Integer.parseInt(quantite.getText()));
    }
       
      
    
}
