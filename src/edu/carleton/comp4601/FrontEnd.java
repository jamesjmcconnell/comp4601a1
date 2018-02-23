package edu.carleton.comp4601;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sda")
public class FrontEnd{

	
@GET
@Produces(MediaType.TEXT_PLAIN)
public String sayPlainTextHello() {
	return "Weclome to the SDA3";
}

@GET
@Produces(MediaType.TEXT_XML)
public String sayXmlHello() {
	return "<?xml version=\"1.0\"?>"+"<sda> Weclome to the SDA2"+ "</sda>";
}


@Path("/search/{terms}")
@GET
@Produces(MediaType.TEXT_HTML)
public String doDistributedSertch(@PathParam("terms") String terms){
	String[] termsSplice = terms.split("\\+");
	String termsFormatted = "<h2>Terms</h2>"
			+ "<ul>";
			
	for(int i = 0; i < termsSplice.length; i++){
		termsFormatted += "<li>" + termsSplice[i] + "</li>";
	}
	termsFormatted += "</ul>";
	return termsFormatted;
}

}