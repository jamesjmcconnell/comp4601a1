package edu.carleton.comp4601.resources;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.DefaultEdge;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.carleton.comp4601.dao.DocumentCollection;

public class SearchableDocumentArchive {
	public DocumentCollection DC;
	public Multigraph<edu.carleton.comp4601.dao.Document, DefaultEdge> graph;
	public MongoClient mongoClient;
	public SearchableDocumentArchive(){
		this.mongoClient = new MongoClient("localhost", 27017);
		this.graph = new Multigraph<edu.carleton.comp4601.dao.Document, DefaultEdge>(DefaultEdge.class);
		this.DC = new DocumentCollection();	
		this.DC.setDocuments(new ArrayList<edu.carleton.comp4601.dao.Document>());
	}
	
	public void getDocuments(){
		if(this.DC.getDocuments().size() == 0){
			List<Document> mongoDocs = this.retreiveDocs();
			this.parseDocs(mongoDocs);
		}else{
			return;
		}
		this.CreateGraph();
		System.out.println("test");
	}
	
	private void parseDocs(List<Document> docs){
		ArrayList<edu.carleton.comp4601.dao.Document> docList = new ArrayList<edu.carleton.comp4601.dao.Document>();
		for(Document doc : docs){
			edu.carleton.comp4601.dao.Document tonyDocument = new edu.carleton.comp4601.dao.Document();	
			tonyDocument.setId(doc.getInteger("docid"));
			tonyDocument.setLinks((ArrayList<String>)doc.get("links"));
			tonyDocument.setName((String)doc.get("name"));
			Float score = (float)0.0;
			tonyDocument.setScore(score);
			tonyDocument.setTags((ArrayList<String>)doc.get("tags"));
			tonyDocument.setText((String)doc.get("text"));
			tonyDocument.setUrl((String)doc.get("url"));
			docList.add(tonyDocument);
		}
		this.DC.setDocuments(docList);
	}
	
	private List<Document> retreiveDocs(){
		MongoDatabase database = mongoClient.getDatabase("SDADB");
		MongoCollection<org.bson.Document> collection = database.getCollection("docs");
		List<Document> documents = (List<Document>) collection.find().into(
				new ArrayList<Document>());
		return documents;
	}
	
	private void CreateGraph(){
		for(edu.carleton.comp4601.dao.Document d: DC.getDocuments()){
			this.graph.addVertex(d);
		}
		for(edu.carleton.comp4601.dao.Document d: DC.getDocuments()){
			for(String link : d.getLinks()){
				edu.carleton.comp4601.dao.Document d2 = findDocByLink(link);
				if(d2.getName() != "not found"){
					graph.addEdge(d, d2);
				}
				
			}
		}
	}
	private edu.carleton.comp4601.dao.Document findDocByLink(String l){
		for(edu.carleton.comp4601.dao.Document d : DC.getDocuments()){
			if(d.getUrl() == l){
				return d;
			}
		}
		edu.carleton.comp4601.dao.Document doc = new edu.carleton.comp4601.dao.Document();
		doc.setName("not found");
		return doc;
	}
}
