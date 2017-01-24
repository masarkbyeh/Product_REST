package edu.ap.jdbc;
import java.sql.* ;

import java.util.ArrayList;
public class JDBConnection {
	
	//JDBC connectie maken 
	private static JDBConnection instance= null; 
	private Connection connection = null; 
	
	private JDBConnection(){}
	
	//Open connectie 
	
	public void openConnection(String database , String user , String pwd){
		try{
			
			//Class.forname = is to load a JDBC driver implementation
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1/" + database;
			//pwd =sql  
			connection = DriverManager.getConnection(url, user , pwd); 
			
		}
		
		catch(Exception ex ){
			System.out.println("Error:" + ex);
		} }
	
	
	
	//close Connectie 
	//als de connectie is niet dicht moet het dicht doen 
	
	
		public void closeConnection(){
		if(connection != null){
		try{
			connection.close();
		}
		
		catch(SQLException ex){
			System.out.println("Error" + ex );
		}
	}
	}
	
	//getJDBConnectie 
	
	public static synchronized JDBConnection getJDBConnection(){
		
		if(instance == null ){
			instance = new JDBConnection(); 
		}
		
		return instance ; 
	}
	

	
	//executeInsert
	public void executeInsert(String table , String Naam , String Categorie , String Datum){
		try{
			
			//wij maken Statement 
			Statement state = connection.createStatement();
			state.executeQuery( "INSERT INTO" + table + "VALUES ('"+Naam+ "','" + Categorie+ "','" + Datum+")");
			state.close();			
			
		}
		
		catch(SQLException ex ){
			System.out.println("Error" + ex );
		}
	}
	
	//selectAll
	
	public ArrayList <String> selectAll(){
		
		ResultSet rs = null ; 
		
		ArrayList<String> result = new ArrayList<String>(); 
		
		try{
			Statement state = connection.createStatement();
			rs= state.executeQuery("SELECT * FROM Product ORDER BY NAAM DESC");
			while (rs.next()){
				result.add(rs.getString(1)+";"+rs.getString(2)+";"+rs.getDate(3));
				
			}
			state.close(); 
			
			
		}
		catch(SQLException ex){
			System.out.println("Error :" + ex);
		}
		return result;
		
		
	}
	
	
	
	
	

}
