/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaapplication1;

import Entite.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class TestpageController implements Initializable {

    private Label Produit;
    @FXML
    private Label titre;
    @FXML
    private Label prix;
    @FXML
    private TextArea description;
    @FXML
    private Label categorie;

    @FXML
    private Label titre11;
    @FXML
    private Label zzzzzz;
    @FXML
    private Label titre1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setProduit(Produit p){
          titre.setText(p.getNom());
        prix.setText(String.valueOf(p.getPrix()));
        description.setText(p.getDescription());
        categorie.setText(String.valueOf(p.getCategorie().getId()));
      
    }
    
}
