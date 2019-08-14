package com.likehuman.lcm.restapi;

/**
 * @author Milo Davis
 * Purpose: This class implements methods defined in MongoLCMDatabase as REST Services, allowing HTTP requests
 * 			to make calls to a specified database.
 */

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import javax.servlet.ServletContext;

import org.bson.Document;

import com.likehuman.lcm.mongodb.LCMDatabaseException;
import com.likehuman.lcm.mongodb.LCMDatabaseFactory;
import com.likehuman.lcm.mongodb.MongoLCMDatabase;


//defines a pathway for the http calls 
@Path("/api/lcm/user/")
public class MongoLCMDatabaseRESTServices
{
	@Context ServletContext context;
	
	
	//receives a post request and posts provided user data into the database
	@POST	
	
	//appends the path with a parameter that is passed to the call, being the userid
	@Path("{userid}")
	public Response postUserService(@PathParam("userid") String userid, String userJSONObject) 
	{	
		try
		{
			//database connection is established and the user is posted
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
			
			db.postUser(userid, userJSONObject);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("User could not be created. Potential server error, consult stack trace.").build();		
		}
		
		//200 response code is returned on success
		return Response.status(200).entity("User Successfully Created").build();
	}
		
	
	
	//receives a get request and retrieves the user from the database
	@GET	
	
	//appends the path with a parameter that is passed to the call, being the userid
	@Path ("{userid}")
	public Response getUserService(@PathParam("userid") String userid) 
	{
		Document foundUser;
		try
		{
			//database connection is established and the user is found
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			foundUser = db.getUser(userid);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("User could not be found.").build();	
		}
		
		//200 response code is returned on success
		return Response.status(200).entity(foundUser).build();
		
	}
	
	
	
	//receives a delete request and deletes the user from the database
	@DELETE 
	
	//appends the path with a parameter that is passed to the call, being the userid
	@Path ("{userid}")
	public Response deleteUserService(@PathParam("userid") String userid) 
	{
		try
		{
			//database connection is established and user is deleted
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			db.deleteUser(userid);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("User either does not exist or could not be deleted.").build();	
		}
	
		//200 response code is returned on success
		return Response.status(200).entity("User was successfully deleted.").build();
	}
	
	
	
	//receives a put request and updates a user from the database
	@PUT
	
	//appends the path with a parameter that is passed to the call, being the userid
	@Path ("{userid}") 
	public Response putUserService(@PathParam("userid") String userid, String userUpdate) 
	{
		try
		{
			//database connection is established and user is updated
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			db.putUser(userid, userUpdate);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("User either does not exist or could not be updated.").build();
		}
		
		//200 response code is returned on success
		return Response.status(200).entity("User was successfully updated.").build();
	}
	
}
