package edu.carleton.comp4601.resources;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class SearchableDocumentArchive {
	
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	@SuppressWarnings("deprecation")
	DB database = mongoClient.getDB("SDADB");
	DBCollection collection = database.createCollection("docs", null);
	
}
