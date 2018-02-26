package edu.carleton.comp4601.resources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import edu.carleton.comp4601.dao.DocumentCollection;

public class SearchableDocumentArchive {
	MongoClient mongoCleint;
	MongoDatabase database;
	MongoCollection<Document> collection;
	DocumentCollection DC;
	
	public SearchableDocumentArchive(){
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("SDADB");
		MongoCollection<Document> collection = database.getCollection("docs");
		DC = new DocumentCollection();
	}
	
	public DocumentCollection getDocuments(){
		if(this.DC.getDocuments().size() == 0){
			List<Document> mongoDocs = this.retreiveDocs();
			this.parseDocs(mongoDocs);
		}else{
			return this.DC;
		}
		return this.DC;
	}
	
	private void parseDocs(List<Document> docs){
		//todo
	}
	private List<Document> retreiveDocs(){
		//todo
		List<Document> documents = (List<Document>) collection.find().into(
				new ArrayList<Document>());
		return documents;
	}
}
