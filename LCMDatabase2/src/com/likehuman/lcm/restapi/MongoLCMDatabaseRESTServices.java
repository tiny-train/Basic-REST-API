package com.likehuman.lcm.restapi;

import javax.ws.rs.DELETE;

/**
 * @author Milo Davis
 * Purpose: This class implements methods defined in MongoLCMDatabase as REST Services, allowing HTTP requests
 * to make calls to a specified database. 
 */


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;


@Path("/api/lcm/user/")
public class MongoLCMDatabaseRESTServices
{
	@POST	
	@Produces(MediaType.TEXT_PLAIN)
	@Path("{userid}")
	public Response postUserService(@PathParam("userid") String userid, String userJSONObject) 
	{	
		try
		{
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "LCMDatabase");
			db.postUser(userid, userJSONObject);
		}
		catch(LCMDatabaseException e)
		{
			return Response.status(404).entity("User could not be created. Please check that file path is correct.").build();		
		}
		
		return Response.status(200).entity("User Successfully Created").build();
	}
		
	
	@GET	
	@Path ("{userid}")
	public Response getUserService(@PathParam("userid") String userid) 
	{
		Document foundUser;
		try
		{
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "LCMDatabase");
		
			foundUser = db.getUser(userid);
		}
		catch(LCMDatabaseException e)
		{
			return Response.status(404).entity("User could not be found.").build();	
		}
		
		System.err.println();
		
		return Response.status(200).entity(foundUser).build();
		
	}
	
	
	
	@DELETE 
	@Path ("{userid}")
	public Response deleteUserService(@PathParam("userid") String userid) 
	{
		try
		{
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "LCMDatabase");
		
			db.deleteUser(userid);
		}
		catch(LCMDatabaseException e)
		{
			return Response.status(404).entity("User either does not exist orcould not be deleted.").build();	
		}
		System.err.println();
		return Response.status(200).entity("User was successfully deleted.").build();
	}
	
	
	
	@PUT
	@Path ("{userid}") 
	public Response putUserService(@PathParam("userid") String userid, String userUpdateFilePath) 
	{
		try
		{
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase("localhost", 27017, "LCMDatabase");
		
			db.putUser(userid, userUpdateFilePath);
		}
		catch(LCMDatabaseException e)
		{
			return Response.status(404).entity("User either does not exist or could not be updated.").build();
		}
		return Response.status(200).entity("User was successfully updated.").build();
	}
	
}
