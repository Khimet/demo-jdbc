package diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/** TP3 - Exercice 1 : Classe éxécutable permettant de faire de l'insertion de données 
 * dans la base de données compta
 * @author Khalil HIMET
 *
 */
public class TestInsertion {

	/**
	 * @param args non utilisés ici
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		/* Objectif de l'exercice : insérer un nouveau fournisseur appellé "La maison de la Peinture" */
		
		/* Etape 1 récupération du fichier de configuration contenant les informations
		 * de connexions à la base de données compta
		 */
//		ResourceBundle fichierConfig = ResourceBundle.getBundle("database");
		
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
			try (Statement statement = connexionBddCompta.createStatement();
					){
				// Requête d'insertion
				int ligneinsere = statement.executeUpdate("insert into FOURNISSEUR (ID, NOM) "
						+ "values(4, 'La Maison de la Peinture')");
				
				// Vérification que l'insertion a bien eu lieu en fait une une requête de sélection
				try(ResultSet resultSet = statement.executeQuery("select * from FOURNISSEUR")){
					
					while (resultSet.next()) {
						
						Integer id = resultSet.getInt("ID");
						String nom = resultSet.getString("NOM");
						
						System.out.println(id +  " " + nom);
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
