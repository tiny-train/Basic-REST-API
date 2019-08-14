package com.likehuman.lcm.restapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.bson.Document;

@Path("/api/lcm/admin/")
public class LCMAdminRESTServices {
	
	//receives a get request and retrieves the user from the database
		@GET	
		
		//appends the path with a parameter that is passed to the call, being the userid
		@Path ("/ping")
		public Response ping() 
		{
			
			Document d = new Document();
			d.append("Status", "OK");
			
			//200 response code is returned on success
			return Response.status(200).entity(d).build();
			
		}

}
