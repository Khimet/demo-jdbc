package diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author Khalil HIMET
 *
 */
public class ComptaBdd {
	
	ResourceBundle bddProperties;

	/** Constructeur
	 * @param bddProperties
	 */
	public ComptaBdd(ResourceBundle bddProperties) {
		super();
		this.bddProperties = bddProperties;
	}
	
	public Statement getStatement() throws SQLException, ClassNotFoundException {
		
		String driverName = bddProperties.getString("database.driver");
		
		Class.forName(driverName);
		
		String url = bddProperties.getString("database.url");
		String utilisateur = bddProperties.getString("database.user");
		String motDePasse = bddProperties.getString("database.pass");
		
		Connection connexionCompta = DriverManager.getConnection(url, utilisateur, motDePasse);
		
		Statement statement = connexionCompta.createStatement();
		
		return statement;
	}
	
	

}
