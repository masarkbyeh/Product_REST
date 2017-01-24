package edu.ap.rest;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import edu.ap.jdbc.JDBConnection;

public class ProductResource extends ServerResource {
	
	@Get 
	public String allStudents(){
		
		//de methode getJdbconnection ophalen van de klasse JDBConnection 
		JDBConnection jdbcon = JDBConnection.getJDBConnection();
		//wij openen hier onze connectie , en wij halen de database en de username
		jdbcon.openConnection("Product", "root", "");
		//wij roepen hier de selectAll methode van klasse JDBConnection
		ArrayList<String> resultArray = jdbcon.selectAll();
		jdbcon.closeConnection();
	
	
	//wij gaan de gegevens van de tabel putten in JsonObject
	
	JSONObject json = new JSONObject (); 
	json.put("operation", "selectAll");
	json.put("length", resultArray.size());
	//wij gaan onze jsonArray gebruiken om onze JSONObject te doorlopen 
	JSONArray jsonArray = new JSONArray(); 
	int i = 0 ; 
	for( String s : resultArray){
		JSONObject obj = new JSONObject () ; 
		obj.put(""+ i , s);
		jsonArray.put(obj);
		i++;
		json.put("result", jsonArray);

		
	} return json.toString();}
	
	@Post("txt")
	
	public void newProduct(String json){
		JSONObject newProduct = new JSONObject(json);
		String Naam = newProduct.getString("Naam");
		String Categorie = newProduct.getString("Categorie"); 
		String Datum = newProduct.getString("Datum");
		
		JDBConnection c = JDBConnection.getJDBConnection();
		c.openConnection("Product", "root", "");
		c.executeInsert("product", Naam, Categorie, Datum);
		c.closeConnection();
		
	}}
	
		
	
	
	
	
	


