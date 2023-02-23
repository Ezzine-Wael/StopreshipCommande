/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.Service.Service;

import Entite.User;
import Utile.DataSource;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ServiceUser implements IService<User> {
    
    private Connection connection; 
    /* Pour faire une seule instance de notre CONNECTION a la BD*/
    public ServiceUser(){
        connection=DataSource.getInstance().getConnection();
    }
    
    public static boolean isValidEmail(String email) {
       String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
       return email.matches(regex);
    }
  
    
    public void AddUser(User t) {
        String requete = "insert into user(email,roles,password,nom,prenom,age,adresse,image,genre,phone,etat) values(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = connection.prepareStatement(requete);
             if (isValidEmail(t.getEmail())) {
             } else {
                 throw new IllegalArgumentException("Invalid email address.");
            }
            pst.setString(1, t.getEmail());
            
            if (!t.getRoles().equals("admin") && !t.getRoles().equals("partenaire")
                    && !t.getRoles().equals("client")) {
                throw new IllegalArgumentException("Invalid role: " + t.getRoles());
            } 
            
            pst.setString(2, t.getRoles());
            pst.setString(3, t.getPassword());
            pst.setString(4, t.getNom());
            pst.setString(5, t.getPrenom());
            pst.setInt(6, t.getAge());
            pst.setString(7, t.getAdresse());
            pst.setString(8, t.getImage());
            pst.setString(9, t.getGenre());
            pst.setString(10, t.getPhone());
            pst.setInt(11, t.getEtat());

            int rowsAdded = pst.executeUpdate();
            if (rowsAdded > 0) {
                System.out.println("User Added successfully.");
            } else {
                System.out.println("User failed to add.");
            }} catch (SQLException ex) {
                System.out.println(ex);
            }    
    }

    
    public void DeleteUser(User t) {
        Scanner sc = new Scanner(System.in);
        try {
            String requete = "DELETE FROM user WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            System.out.println("***ENTER ID OF THE USER YOU WANT TO DELETE***");
            int id = sc.nextInt();
            pst.setInt(1, id);
            
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            } } catch (SQLException ex) {
                System.out.println(ex);
            }  
    }

    public void UpdateUser(User t) {
        try {
            String requete = "UPDATE user SET nom = ?, prenom = ?, age = ? WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(requete);
            
            pst.setString(1, t.getNom());
            
            
           
            pst.setString(2,t.getPrenom());
            
          
            pst.setInt(3,t.getAge() );
            
           
            pst.setInt(4,t.getId() );
            
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User failed to update.");
            } 
        } catch (SQLException ex) {
            System.out.println("Failure to upload data");
            System.out.println(ex);
        }    
    }

    public List<User> ReadAllUsers() {
        List<User> list=new ArrayList<>();
                String requete="select * from user";
            try {
                Statement st=connection.createStatement();
                ResultSet rs=st.executeQuery(requete);
                
                while(rs.next()){
                    User u =new User(rs.getInt("id"), rs.getString("email"),rs.getString("roles"), 
             rs.getString("password"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("age"),
                 rs.getString("adresse"),rs.getString("image"),
                   rs.getString("genre"),rs.getString("phone"),rs.getInt("etat"));
                    list.add(u);
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return list;      
    }

    @Override
    public void insert(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User readById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
