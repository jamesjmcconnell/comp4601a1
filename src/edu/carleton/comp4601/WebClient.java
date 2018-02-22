package edu.carleton.comp4601;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sda")
public class WebClient {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Weclome to the SDA";
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXmlHello() {
		return "<?xml version=\"1.0\"?>"+"<sda> Weclome to the bank XML"+ "</sda>";
	}

//	private static String BASE_URL = "http://localhost:8080/sda";
//	
//	private void run() {
//		ClientConfig config = new DefaultClientConfig();
//		Client client = Client.create(config);
//		WebResource service = client.resource(getBaseURI());
//	}
//	
//	private static URI getBaseURI() {
//		return UriBuilder.fromUri(BASE_URL).build();
//	}
}
