package edu.carleton.comp4601.simple;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bank")
public class Bank {

@GET
@Produces(MediaType.TEXT_PLAIN)
public String sayPlainTextHello() {
	return "Weclome to the bank Plain text";
}

@GET
@Produces(MediaType.TEXT_XML)
public String sayXmlHello() {
	return "<?xml version=\"1.0\"?>"+"<bank> Weclome to the bank XML"+ "</bank>";
}

}