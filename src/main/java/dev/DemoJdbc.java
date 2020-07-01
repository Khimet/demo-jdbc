package dev;

import java.sql.*;
import java.util.ResourceBundle;

public class DemoJdbc {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Lecture du fichier de propriétés
        ResourceBundle database = ResourceBundle.getBundle("database");


        // Etape 1 - Enregistrer le pilote
        // => indiquer à JDBC une possibilité de communiquer avec une base
        // DriverManager.registerDriver(new Driver());
        Class.forName(database.getString("database.driver"));

        // Etape 2 - Créer une connexion
        String url = database.getString("database.url"); // url JDBC d'accès à la base (machine, port, le nom de la base, le type de base...)
        String utilisateur = database.getString("database.user");
        String motDePasse =  database.getString("database.pass");
        Connection uneConnexion = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            uneConnexion = DriverManager.getConnection(url, utilisateur, motDePasse);

            // Etape 3 - Créer un statement
            statement = uneConnexion.createStatement();


            // int nbLignesImpactees = statement.executeUpdate("insert into plat(nom, prix) values('super plat 1', 1560)");

            // Etape 4 - Exécuter la requête
            resultSet = statement.executeQuery("select * from pat");

            // Etape 5 - Exploiter les résultats
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                Integer prix = resultSet.getInt("prix");

            }
        } finally {
            // Etape 6 - fermer les resssources
            if(resultSet != null) {
                resultSet.close();
            }

            if(statement != null) {
               statement.close();
           }

           if(uneConnexion != null) {
               uneConnexion.close();
           }

        }



    }
}
