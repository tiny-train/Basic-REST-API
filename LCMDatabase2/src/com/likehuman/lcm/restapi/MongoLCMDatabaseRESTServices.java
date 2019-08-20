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
@Path("/api/lcm/")

public class MongoLCMDatabaseRESTServices
{
	//Servlet Context here defers to variables defined in the web.xml for database connections
	@Context ServletContext context;
	
	
	
	
	
	//---------------User Functions---------------//
	
	//receives a post request and posts provided user data into the database
	@POST	
	
	//appends the path with a parameter that is passed to the call, being the userid
	@Path("user/{userid}")
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
	@Path ("user/{userid}")
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
	@Path ("user/{userid}")
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
	@Path ("user/{userid}") 
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
	
	
	
	
	
	//---------------Dataset Functions---------------//
	
	//receives a post request and posts provided dataset into the database
	@POST	
	
	//appends the path with a parameter that is passed to the call, being the datasetid
	@Path("dataset/{datasetid}")
	public Response postDatasetService(@PathParam("datasetid") String datasetid, String datasetJSONObject) 
	{	
		try
		{
			//database connection is established and the dataset is posted
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
			
			db.postDataset(datasetid, datasetJSONObject);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Dataset could not be created. Potential server error, consult stack trace.").build();		
		}
		
		//200 response code is returned on success
		return Response.status(200).entity("Dataset Successfully Created").build();
	}
			
		
		
	//receives a get request and retrieves the dataset from the database
	@GET	
		
	//appends the path with a parameter that is passed to the call, being the datasetid
	@Path ("dataset/{datasetid}")
	public Response getDatasetService(@PathParam("datasetid") String datasetid) 
	{
		Document foundDataset;
		try
		{
			//database connection is established and the user is found
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			foundDataset = db.getDataset(datasetid);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Dataset could not be found.").build();	
		}
			
		//200 response code is returned on success
		return Response.status(200).entity(foundDataset).build();
		
	}
		
		
		
	//receives a delete request and deletes the dataset from the database
	@DELETE 
		
	//appends the path with a parameter that is passed to the call, being the datasetid
	@Path ("dataset/{datasetid}")
	public Response deleteDatasetService(@PathParam("datasetid") String datasetid) 
	{
		try
		{
			//database connection is established and dataset is deleted
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			db.deleteDataset(datasetid);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Dataset either does not exist or could not be deleted.").build();	
		}
		
		//200 response code is returned on success
		return Response.status(200).entity("Dataset was successfully deleted.").build();
	}
		
		
		
	//receives a put request and updates a dataset from the database
	@PUT
		
	//appends the path with a parameter that is passed to the call, being the datasetid
	@Path ("dataset/{datasetid}") 
	public Response putDatasetService(@PathParam("datasetid") String datasetid, String datasetUpdate) 
	{
		try
		{
			//database connection is established and dataset is updated
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			db.putDataset(datasetid, datasetUpdate);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Dataset either does not exist or could not be updated.").build();
		}
			
		//200 response code is returned on success
		return Response.status(200).entity("Dataset was successfully updated.").build();
	}
	
	
	
	
	
	//---------------Group Functions---------------//
	
	//receives a post request and posts provided group data into the database
	@POST	
	
	//appends the path with a parameter that is passed to the call, being the groupid
	@Path("group/{groupid}")
	public Response postGroupService(@PathParam("groupid") String groupid, String groupJSONObject) 
	{	
		try
		{
			//database connection is established and the group is posted
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
			
			db.postGroup(groupid, groupJSONObject);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Group could not be created. Potential server error, consult stack trace.").build();		
		}
		
		//200 response code is returned on success
		return Response.status(200).entity("Group Successfully Created").build();
	}
		
	
	
	//receives a get request and retrieves the group from the database
	@GET	
	
	//appends the path with a parameter that is passed to the call, being the groupid
	@Path ("group/{groupid}")
	public Response getGroupService(@PathParam("groupid") String groupid) 
	{
		Document foundGroup;
		try
		{
			//database connection is established and the group is found
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			foundGroup = db.getGroup(groupid);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Group could not be found.").build();	
		}
		
		//200 response code is returned on success
		return Response.status(200).entity(foundGroup).build();
		
	}
	
	
	
	//receives a delete request and deletes the group from the database
	@DELETE 
	
	//appends the path with a parameter that is passed to the call, being the groupid
	@Path ("group/{groupid}")
	public Response deleteGroupService(@PathParam("groupid") String groupid) 
	{
		try
		{
			//database connection is established and user is deleted
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			db.deleteGroup(groupid);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Group either does not exist or could not be deleted.").build();	
		}
	
		//200 response code is returned on success
		return Response.status(200).entity("Group was successfully deleted.").build();
	}
	
	
	
	//receives a put request and updates a group from the database
	@PUT
	
	//appends the path with a parameter that is passed to the call, being the groupid
	@Path ("group/{groupid}") 
	public Response putGroupService(@PathParam("groupid") String groupid, String groupUpdate) 
	{
		try
		{
			//database connection is established and group is updated
			MongoLCMDatabase db = LCMDatabaseFactory.getMongoLCMDatabase(context);
		
			db.putGroup(groupid, groupUpdate);
		}
		catch(LCMDatabaseException e)
		{
			//errors are displayed and and a 404 message is handled if a problem occurs
			e.printStackTrace(System.err);
			return Response.status(404).entity("Group either does not exist or could not be updated.").build();
		}
		
		//200 response code is returned on success
		return Response.status(200).entity("Group was successfully updated.").build();
	}
		
	
}
