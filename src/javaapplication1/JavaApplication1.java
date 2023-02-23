/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import Entite.Commande;
import Entite.User;
import Utile.TunisieMap.State;
import Utile.TunisieMap.TunisieMap;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javaapplication1.Service.Service.Service_Commande;

/**
 *
 * @author Plop
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            User user=new User(2);
            Commande commande=new Commande(user,"za",12,"Active");
            Commande commande2=new Commande(2);
            commande.setId(7);
      //      commande.setEtat("Active");
          Service_Commande sc=new Service_Commande();
          //System.out.println(sc.readAllcount());
          //  System.out.println(sc.CountByDate(Date.valueOf(LocalDate.now())));
         // System.out.println(sc.CountByUserByDate(user,Date.valueOf(LocalDate.now())));
      //   System.out.println(sc.readAllCommandeStatus("Active"));
      //  System.out.println(sc.readById(4));
          //  sc.updateAdminUse(commande);
          //  System.out.println(sc.insertCommande(commande));

     /*    ArrayList<State> tunisiemap = TunisieMap.getInstance().getAdjacencyList();
        
          tunisiemap.forEach(e->{
         
              System.out.println(e.getKey());
        e.getValue().forEach(a->{
            System.out.println("   "+tunisiemap.get(a).getKey());
        });
        });*/
        //commande.setDate(Date.valueOf(LocalDate.now()));
        //sc.updateAdminUse(commande);

    }
    
}
