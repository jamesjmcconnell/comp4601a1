package edu.carleton.comp4601.resources;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.carleton.comp4601.dao.DocumentCollection;

public class SearchableDocumentArchive {
	
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase database = mongoClient.getDatabase("SDADB");
	MongoCollection<Document> collection = database.getCollection("docs");
	
	DocumentCollection DC = new DocumentCollection();
	
}
