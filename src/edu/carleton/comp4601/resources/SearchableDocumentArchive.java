package edu.carleton.comp4601.resources;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoClient;
import org.bson.Document;

public class SearchableDocumentArchive {
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase database = mongoClient.getDatabase("SDADB");
	MongoCollection<Document> collection = database.getCollection("docs");
}
