package diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author Khalil HIMET
 *
 */
public class TestDelete {

	/**
	 * @param args non utilisés ici
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		// Objectif de l'exercice : supprimer le fournisseur "La Maison des Peintures"
		
		ResourceBundle fichierConfig = ResourceBundle.getBundle("database2");
		
String driverName = fichierConfig.getString("database.driver");
		
		// Etape 2 chargement/enregistrement du driver
		Class.forName(driverName);
		
		// Etape 3 Etablissement de la conexion à la base de données compta
		String url = fichierConfig.getString("database.url");
		String utilisateur = fichierConfig.getString("database.user");
		String motDePasse = fichierConfig.getString("database.pass");
		
		// Connexion à la base de données compta
		try (Connection connexionBddCompta = DriverManager.getConnection(url, utilisateur, motDePasse)) {
			
			// La transaction à exécuter se compose d'une requête de sélection et une d'insertion
			connexionBddCompta.setAutoCommit(false);
			
			// Creation du statement
			try(Statement statement = connexionBddCompta.createStatement();
					){
				
				// Requête de suppression
				int lignesupprimee = statement.executeUpdate("delete from FOURNISSEUR where NOM = 'La Maison des Peintures'");
				
				try(ResultSet resultSet = statement.executeQuery("select * from FOURNISSEUR")){
					
					while (resultSet.next()) {
						
						Integer id = resultSet.getInt("ID");
						String nom = resultSet.getString("NOM");
						
						System.out.println(id + " " + nom);
					}
				}
				
				// Commit de la transaction
				connexionBddCompta.commit();
			} catch (SQLException e) {
				
				connexionBddCompta.rollback();
			}
		

		
		

	}

}
}
