package dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/** Class éxécutable de test de connexion à la base de données compta
 * @author Khalil HIMET
 *
 */
public class TestConnexionJdbc {

	/**
	 * @param args non utilisés ici
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		/* TP01 - se connecter à la base de données compta et afficher la connexion
		 * avec un System.out.println.
		 */
		
		/* Etape 1 récupération du fichier de configuration contenant les informations
		 * de connexions à la base de données compta
		 */
		ResourceBundle fichierConfig = ResourceBundle.getBundle("database");
		
		String driverName = fichierConfig.getString("database.driver");
		
		// Etape 2 chargement/enregistrement du driver
		Class.forName(driverName);
		
		// Etape 3 Etablissement de la conexion à la base de données compta
		String url = fichierConfig.getString("database.url");
		String utilisateur = fichierConfig.getString("database.user");
		String motDePasse = fichierConfig.getString("database.pass");
		Connection connexionBaseCompta = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Connexion à la base de données compta
			connexionBaseCompta = DriverManager.getConnection(url, utilisateur, motDePasse);
			
			// Creation d'un statement
			statement = connexionBaseCompta.createStatement();
			
			// Test d'une requête
			resultSet = statement.executeQuery("select * from article");
			
			// Affichage des résultats
			while(resultSet.next()) {
				
				Integer id = resultSet.getInt("id");
				String designation = resultSet.getString("designation");
				Float prix = resultSet.getFloat("prix");
				
				System.out.println(id + " " + designation + " " + prix);
			}
			
			
			
			
			
		} finally {
			
			// Fermeture de la connexion
			
			if (connexionBaseCompta != null) {
				
				connexionBaseCompta.close();
			} else {
				
				System.err.println("La connexion n'a pas pu se faire");
			}
			
			if (statement != null) {
				
				statement.close();
			}
			
			if (resultSet != null) {
				resultSet.close();
			}
		}
		

	}

}
