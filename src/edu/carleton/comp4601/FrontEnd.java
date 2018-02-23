package edu.carleton.comp4601;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sda")
public class FrontEnd{

@GET
@Produces(MediaType.TEXT_PLAIN)
public String sayPlainTextHello() {
	return "Weclome to the SDA";
}

@GET
@Produces(MediaType.TEXT_XML)
public String sayXmlHello() {
	return "<?xml version=\"1.0\"?>"+"<sda> Weclome to the SDA"+ "</sda>";
}

}