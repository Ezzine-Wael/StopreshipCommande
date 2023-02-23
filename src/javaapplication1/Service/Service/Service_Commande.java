/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1.Service.Service;

import Entite.Commande;
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
public class Service_Commande implements IService<Commande> {
    /*
    provided Methodes 
    insert    Insert a new command NB : the fields 'id' , 'date' are not required
    insertCommande(commande)    Insert a new command and retrieve the AUTO generated ID in the same command
    delete NB: not realy gonna be used EVER
    Readall() Select * from commande 
    readallForuser( user )  toute les commande de USER     select * from commande where id_user=user.getId()
    readByid ( id_commande ) renvoie une seule commande avec la méme ID       select * from commande 
    update (commande )   faire la mise a jour sur une commande
                NB:: Le seul champs qui sera modifier est le champ ETAT
                NB ::  Aucun Autre ne peut étré MODFIER JAMAIS c'est concréte
    readAllCommandeStatus(etat) Select * from commande where etat=etat
                NB:: affichage depend du etat
    readAllcount   NB: Return the number of ALL commands
    readAllByUser(user)              read all by user
    readAllByUserByDate(user,date)   read all by user by date
    
    countAllByUser(user)              count all by user
    countAllByUserByDate(user,date)   count all by user by date
    updateAdminUse(commande)  update a commande NB: FOR ADMIN USE ONLY AND TESTING 
    */
    private Connection connection; 
    /* Pour faire une seule instance de notre CONNECTION a la BD*/
    public Service_Commande(){
        connection=DataSource.getInstance().getConnection();
    }

 // test 
    /*Insertion COmmande*/
    @Override
    public void insert(Commande commande) {
   /*   Structure de la Table Commande 
        id;               AUTO INCREMENT                        CAN SKIP INSERT
      user;                FOREIGN KEY                              MUST INSERT
      destination;                                                  MUST INSERT
      prix;                                                         MUST INSERT
      etat;                                                         MUST INSERT
      date;   DEFAULT : current SYstem.timestamp() in the DB     CAN SKIP INSERT
*/
        String requete = "insert into commande (id_user,destination,prix,etat) values "
                + "('" +
                commande.getUser().getId() + "','" +
                commande.getDestination() + "'," +
                commande.getPrix() + ",'"+
                commande.getEtat() +"')";
        try {
            Statement st = connection.createStatement();
            st.executeUpdate(requete);
            System.out.println("Commande Ajouté avec Success");
        } catch (SQLException ex) {
                System.out.println("Insertion Commande Failure");
                System.out.println(ex);
                        
        }
    }
    public Commande insertCommande(Commande commande){
        /*   Structure de la Table Commande 
        id;               AUTO INCREMENT                        CAN SKIP INSERT
      user;                FOREIGN KEY                              MUST INSERT
      destination;                                                  MUST INSERT
      prix;                                                         MUST INSERT
      etat;                                                         MUST INSERT
      date;   DEFAULT : current SYstem.timestamp() in the DB     CAN SKIP INSERT
*/
        String requete = "insert into commande (id_user,destination,prix,etat) values "
                + "('" +
                commande.getUser().getId() + "','" +
                commande.getDestination() + "'," +
                commande.getPrix() + ",'"+
                commande.getEtat() +"')";
        try {
            PreparedStatement st = connection.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
            st.executeUpdate();
            ResultSet generatedKeys=st.getGeneratedKeys();
            if (generatedKeys.next()) {
    // retrieve the auto-generated ID from the ResultSet
            commande.setId(generatedKeys.getInt(1));
            System.out.println("Commande Ajouté avec Success");
        }} catch (SQLException ex) {
                System.out.println("Insertion Commande Failure");
                System.out.println(ex);
                        
        }
        return commande;
    }
    @Override
    public void delete(Commande commande) {
           /* NEVER REALY GONNA BE USED AS A FUNCTION AS ALL COMMANDES WILL CHANGE THE 'etat' COLUMN */
              String requete = "DELETE FROM commande WHERE id = '"+commande.getId()+"'";
        try {
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commande: "+commande.getId()  +"    from User :" +commande.getUser().getId()+ "has been deleted");
        } catch (SQLException ex) {
               System.out.println(ex);
        }


    }

    @Override
    public void update(Commande commande) {
        String requete = "UPDATE commande SET Etat = '"+commande.getEtat()+"' WHERE id="+ commande.getId();
        try {
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commande Modifier avec Etat "+commande.getEtat());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public int readAllcount() { /*Return the number of all commands*/
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
     int count=0;
     String requete="select count(*) from commande";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            if(rs.next())
            count=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }
    public int CountByDate(Date date) { /*Return the number of all commands by Date*/
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
     int count=0;
     
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     String dateStr = dateFormat.format(date);
   
     String requete="select count(*) from commande WHERE date= '"+dateStr +"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            if(rs.next())
            count=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }
    public List<Commande> readAllByDate(Date date){
        /*Return the number of all commands by Date*/
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
        List<Commande> list_commande=new ArrayList<Commande>();
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     String dateStr = dateFormat.format(date);
     String requete="select * from commande WHERE date= '"+dateStr +"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Commande commande=new Commande(rs.getInt(1), new User(rs.getInt(2)),
                        rs.getString(3), rs.getFloat(4),
                        rs.getString(5),rs.getDate(6));
                list_commande.add(commande);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_commande;
    }
    public List<Commande> readAllByUserByDate(User user,Date date){
        /*Return the number of all commands
                                                                  By User By date
        */
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
     List<Commande> list_commande=new ArrayList<Commande>();
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     String dateStr = dateFormat.format(date);
     String requete="select * from commande WHERE id_user='"+user.getId()+"' AND date='"+dateStr+"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
             while(rs.next()){
                Commande commande=new Commande(rs.getInt(1), new User(rs.getInt(2)),
                        rs.getString(3), rs.getFloat(4),
                        rs.getString(5),rs.getDate(6));
                list_commande.add(commande);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_commande;
    }
    
    public int CountByUser(User user) { /*Return the number of all commands by User*/
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
     int count=0;
     String requete="select count(*) from commande WHERE id_user='"+user.getId()+"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            if(rs.next())
            count=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }
    public int CountByUserByDate(User user,Date date) { /*Return the number of all commands
                                                                  By User By date
        */
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
     int count=0;
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     String dateStr = dateFormat.format(date);
     String requete="select count(*) from commande WHERE id_user='"+user.getId()+"' AND date='"+dateStr+"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            if(rs.next())
            count=rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }
    @Override
    public List<Commande> readAll() {
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
        List<Commande> list_commande=new ArrayList<>();
        String requete="select * from commande";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Commande commande=new Commande(rs.getInt(1), new User(rs.getInt(2)),
                        rs.getString(3), rs.getFloat(4),
                        rs.getString(5),rs.getDate(6));
                list_commande.add(commande);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_commande;
    }
    
    public List<Commande> readAllCommandeStatus(String status) {
     /*   Structure de la Table Commande  READ ALL tout depends de le champs 'etat' de la BD
        id;                                          
      user;           
      destination;     
      prix;
      etat;      Filtered 
      date;
        */
        List<Commande> list_commande=new ArrayList<>();
        String requete="select * from commande WHERE etat='"+status+"'";
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Commande commande=new Commande(rs.getInt(1), new User(rs.getInt(2)),
                        rs.getString(3), rs.getFloat(4),
                        rs.getString(5),rs.getDate(6));
                list_commande.add(commande);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_commande;
    }
    public List<Commande> readAllCommandeUser(User user) {
     /*   Structure de la Table Commande  READ ALL
        id;                                          
      user;           ;
      destination;     
      prix;
      etat;
      date;
        */
        List<Commande> list_commande=new ArrayList<>();
        String condition="id_user="+user.getId();
        String requete="select * from commande"+condition;
        try {
            Statement st=connection.createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Commande commande=new Commande(rs.getInt(1), new User(rs.getInt(2)),
                        rs.getString(3), rs.getFloat(4),
                        rs.getString(5),rs.getDate(6));
                list_commande.add(commande);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list_commande;
     }
    @Override
    public Commande readById(int id) {
        /*   Structure de la Table Commande 
        id;                            MUST provide               
      user;           ;
      destination;     
      prix;
      etat;
      date;
*/
         Commande new_commande=new Commande();
        String condition = ("id ='"+id+"'");
        String requete="SELECT * FROM commande WHERE " + condition;
        try {
            PreparedStatement st=connection.prepareStatement(requete);
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                 new_commande=new Commande(rs.getInt("id"),new User( rs.getInt(2)),
                        rs.getString(3), rs.getFloat(4),rs.getString(5),
                         rs.getDate(6)
                 );
            }
        } catch (SQLException e) {
            System.out.println("Error ReadByID Exception = \n"+e );
        }
        return new_commande;


    }
    
    public void updateAdminUse(Commande commande) {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     String dateStr = dateFormat.format(commande.getDate());
       String requete = "UPDATE commande SET Etat = '" + commande.getEtat() + "', " +
                 "destination =  '" + commande.getDestination() + "', " +
                 "prix = '" + commande.getPrix() + "', " +
                 "id_user = " + commande.getUser().getId() +","+
                 "date='"+commande.getDate()+
                 "' WHERE id = '" + commande.getId() + "'";
        try {
            PreparedStatement pst = connection.prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Commande Modifier avec Etat "+commande.getEtat());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    
   
}
