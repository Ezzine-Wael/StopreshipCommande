/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javaapplication1.GUI.Controllers;

import Entite.Commande;
import Entite.Detail_Commande;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javaapplication1.Service.Service.Service_Detail_Commande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Plop
 */
public class CommandeController implements Initializable {

    @FXML
    private AnchorPane comm;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label destination;
    @FXML
    private Label prix;
    @FXML
    private Button detail;
    String path="/javaapplication1/GUI/FXMLpages/"; /*must insert in every getResources*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("trying");
    }    
    public void setCommande(Commande c){
        id.setText(String.valueOf(c.getId()));
        date.setText(String.valueOf(c.getDate()));
        destination.setText(c.getDestination());
    }

    @FXML
    private void extraDetail(ActionEvent event) {
        Service_Detail_Commande sdc=new Service_Detail_Commande();
         List<Detail_Commande> list=new ArrayList<>();
         System.out.println("getting List ");
         list=sdc.readallByCommande(Integer.parseInt(id.getText()));
         System.out.println("this is the list of the detailllsssssssssssssssssss"+list);
         System.out.println("la commande est "+id.getText());
         
         HolderpageController holderController=new HolderpageController();
         holderController.populateproduit(list);
    }
}
