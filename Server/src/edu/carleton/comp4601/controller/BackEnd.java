package edu.carleton.comp4601.controller;

import javax.ws.rs.*;
import edu.carleton.comp4601.resources.*;
import javax.ws.rs.core.MediaType;

@Path("/sda")
public class BackEnd{

	
@GET
@Produces(MediaType.TEXT_PLAIN)
public String sayPlainTextHello() {
	return "Weclome to the SDA Server by Alex Carlucci and James Mcconnell";
}


@GET
@Produces(MediaType.TEXT_XML)
public String sayXmlHello() {
	return "<?xml version=\"1.0\"?> <sda> Weclome to the SDA Server by Alex Carlucci and James Mcconnell </sda>";
}

@Path("/try")
@Produces(MediaType.TEXT_PLAIN)
public String testStuff(){
	SearchableDocumentArchive sda = new SearchableDocumentArchive();
	sda.getDocuments();
	return "dosent matter";
}

}
