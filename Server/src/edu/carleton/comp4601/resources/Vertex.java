package edu.carleton.comp4601.resources;
import java.util.ArrayList;

import edu.carleton.comp4601.dao.Document;

public class Vertex {
	Document content;
	ArrayList<Edge> edges;
	
	public Vertex(){
		this.edges = new ArrayList<Edge>();
	}
	public Vertex(Document d, ArrayList<Edge> e){
		this.content = d;
		this.edges = e;
	}
	public Vertex(Document d){
		this.content = d;
		this.edges = new ArrayList<Edge>();
	}
	public void addEdge(Edge e){
		this.edges.add(e);
	}
	public void setContent(Document d){
		this.content = d;
	}
}
