/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.Service.Service;

import Entite.Categorie;
import Entite.Commande;
import Entite.Produit;
import Entite.User;
import Utile.DataSource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 *
 * @author Plop
 */
public class ServiceProduit implements IService<Produit> {
  private Connection connection; 
    /* Pour faire une seule instance de notre CONNECTION a la BD*/
    public ServiceProduit(){
        connection=DataSource.getInstance().getConnection();
    }
    @Override
    public void insert(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Produit t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Produit> readAll() {
             List<Produit> list_produit=new ArrayList<>();
        String requete="select * from produit";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Produit produit=new Produit(rs.getInt(1), rs.getString(2),
                        rs.getString(3),new Categorie(rs.getInt(4)),
                        rs.getFloat(5),rs.getInt(6),
                        rs.getString(7),rs.getString(8));
                list_produit.add(produit);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_produit;
    }

    @Override
    public Produit readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
   
}
